package picpaydesafiobackend.common.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.application.config.SecurityContext;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.common.service.TokenService;

import javax.crypto.SecretKey;
import jakarta.servlet.http.HttpServletRequest;


import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

import static picpaydesafiobackend.application.config.SecurityContext.JWT_KEY;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final UserService userService;

    public String  generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("claims", populateClaims(user));

        return createToken(claims, user.getEmail());
    }

    public void checkAccessToken(HttpServletRequest request) throws UserException {
        String accessToken = request.getHeader(SecurityContext.HEADER);

        if (accessToken == null || !validateToken(accessToken)) {
            throw new UserException("Sessão expirada, você precisa realizar o login novamente.");
        }
    }

    public Boolean validateToken(String token) throws UserException {
        String email = extractEmail(token);

        User user = userService.findUserByEmail(email);

        if(user == null) {
            return false;
        }

        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    public User validateTokenAndGetUser(String token) throws UserException {
        if (isTokenExpired(token)) {
            return null;
        }
        String email = extractEmail(token);
        return userService.findUserByEmail(email);
    }

    /*public String extractUserId(HttpServletRequest request) {
        String accessToken = request.getHeader(SecurityContext.HEADER);
        Claims claims = extractAllClaims(accessToken);

        return extractClaims(accessToken, claims.get("id", String.class));
    }*/

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String  extractUserEmail(HttpServletRequest request) {
        String accessToken = request.getHeader(SecurityContext.HEADER);

        return extractClaims(accessToken, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
    }

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private String populateClaims(User user) {
        Set<String> claims = new HashSet<>();

        String id = user.getId();
        claims.add(user.getEmail());
        claims.add(user.getTipoPessoa());
        claims.add(id);

        return String.join(",", claims);
    }

    public String createToken(Map<String, Object> claims, String subject) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuer("Picpay - API DESAFIO")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(generateKey(), SignatureAlgorithm.HS256);

        return jwtBuilder.compact();
    }
}
