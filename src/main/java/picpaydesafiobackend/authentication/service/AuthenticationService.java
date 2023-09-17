package picpaydesafiobackend.authentication.service;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.LoginRequest;

public interface AuthenticationService {
    User autenticar(LoginRequest loginRequest, User user) throws UserException;
}
