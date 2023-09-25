package picpaydesafiobackend.common.service;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;

import jakarta.servlet.http.HttpServletRequest;



public interface TokenService {
    String  generateToken(User user);
    void checkAccessToken(HttpServletRequest request) throws UserException;
    //String extractUserId(HttpServletRequest request);
    String extractUserEmail(HttpServletRequest request);
    User validateTokenAndGetUser(String token) throws UserException;
}
