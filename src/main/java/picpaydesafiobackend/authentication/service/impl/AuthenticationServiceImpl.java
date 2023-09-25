package picpaydesafiobackend.authentication.service.impl;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.LoginRequest;
import picpaydesafiobackend.authentication.payload.response.AuthenticatedUserResponse;
import picpaydesafiobackend.authentication.repository.UserRepository;
import picpaydesafiobackend.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.service.TokenService;
import picpaydesafiobackend.common.utils.MapResponses;
import picpaydesafiobackend.common.utils.TipoPessoa;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final TokenService tokenService;
    private final PessoaService pessoaService;
    private final MapResponses mapResponses;

    public AuthenticatedUserResponse autenticar(LoginRequest loginRequest, User user) throws UserException {

        if (!isValidPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new UserException("A senha informada está incorreta.");
        }

        String token = tokenService.generateToken(user);

        user.setDtUltimoLogin(LocalDateTime.now());

        Pessoa pessoa = user.getTipoPessoa().equalsIgnoreCase(TipoPessoa.FISICA.getAbbreviation())
                ? pessoaService.findPessoaFisicaByUsuarioId(user.getId()) :
                pessoaService.findPessoaJuridicaByUsuarioId(user.getId());

        return mapResponses.mapToAuthenticatedUserResponse(user, pessoa, token);
    }

    private boolean isValidPassword(String inputPassword, String storedPassword) {
        return BCrypt.checkpw(inputPassword, storedPassword);
    }
}
