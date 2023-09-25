package picpaydesafiobackend.authentication.service;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.LoginRequest;
import picpaydesafiobackend.authentication.payload.response.AuthenticatedUserResponse;

public interface AuthenticationService {
    AuthenticatedUserResponse autenticar(LoginRequest loginRequest, User user) throws UserException;
}
