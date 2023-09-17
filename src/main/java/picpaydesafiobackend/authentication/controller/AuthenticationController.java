package picpaydesafiobackend.authentication.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.LoginRequest;
import picpaydesafiobackend.authentication.service.AuthenticationService;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.utils.MapResponses;
import picpaydesafiobackend.common.utils.MessageResponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final MessageResponseService messageResponseService;
    private final MapResponses mapResponses;

    @PostMapping("login")
    public ResponseEntity<MessageResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest) {
        MessageResponseDTO messageResponseDTO;
        try {
            User user = userService.findUserByEmail(loginRequest.getEmail());
            validateUser(user);

            User userAuthored = authenticationService.autenticar(loginRequest, user);

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Login efetuado com sucesso!", mapResponses.mapToUserResponse(userAuthored), "");

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao efetuar login", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }

    }

    private void validateUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("Usuário não encontrado na base de dados.");
        }
    }
}
