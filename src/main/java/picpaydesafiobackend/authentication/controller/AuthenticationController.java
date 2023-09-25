package picpaydesafiobackend.authentication.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import picpaydesafiobackend.application.config.SecurityContext;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.LoginRequest;
import picpaydesafiobackend.authentication.payload.response.AuthenticatedUserResponse;
import picpaydesafiobackend.authentication.service.AuthenticationService;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.utils.MapResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.AUTENTICACAO)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final MessageResponseService messageResponseService;

    @PostMapping("login")
    public ResponseEntity<MessageResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest) {
        MessageResponseDTO messageResponseDTO;
        try {
            User user = userService.findUserByEmail(loginRequest.getEmail());
            validateUser(user);

            AuthenticatedUserResponse userAuthored = authenticationService.autenticar(loginRequest, user);

            HttpHeaders httpHeaders = createAuthenticationHeader(userAuthored.getToken());

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Login efetuado com sucesso!", userAuthored, "");

            return ResponseEntity.ok().headers(httpHeaders).body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao efetuar login", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }

    }

    private HttpHeaders createAuthenticationHeader(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityContext.HEADER, token);
        return httpHeaders;
    }

    private void validateUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("Usuário não encontrado na base de dados.");
        }
    }
}
