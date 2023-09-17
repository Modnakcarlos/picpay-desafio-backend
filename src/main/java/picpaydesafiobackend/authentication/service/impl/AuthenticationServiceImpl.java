package picpaydesafiobackend.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.LoginRequest;
import picpaydesafiobackend.authentication.repository.UserRepository;
import picpaydesafiobackend.authentication.service.AuthenticationService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    public User autenticar(LoginRequest loginRequest, User user) throws UserException {

        if (!isValidPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new UserException("A senha informada está incorreta.");
        }

        user.setDtUltimoLogin(LocalDateTime.now());
        return userRepository.save(user);
    }

    private boolean isValidPassword(String inputPassword, String storedPassword) {
        return BCrypt.checkpw(inputPassword, storedPassword);
    }
}
