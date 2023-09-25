package picpaydesafiobackend.authentication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.UserRequest;
import picpaydesafiobackend.authentication.service.impl.UserServiceImpl;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import picpaydesafiobackend.common.utils.MapResponses;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(Routes.USER)
public class userController {

    private final UserServiceImpl userService;
    private final CustomBuilders customBuilders;
    private final MapResponses mapResponses;
    private final PessoaService pessoaService;
    @PostMapping("/criar-usuario")
    @Transactional
    public ResponseEntity<MessageResponseDTO> createUser(@Valid @RequestBody UserRequest userRequest) {
        MessageResponseDTO responseDTO;
        try {
            User user = userService.createUser(userRequest);

            pessoaService.createPessoa(user);

            responseDTO = customBuilders.BuildMessageDTO(true,
                    "Usuário criado com sucesso!", mapResponses.mapToUserResponse(user), "" );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDTO);
        }
        catch (Exception e) {
            responseDTO = customBuilders.BuildMessageDTO(false,
                    "Falha ao criar usuário", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }
    }



    @GetMapping("get-todos-users")
    public ResponseEntity<MessageResponseDTO> findAllUsers() {
        MessageResponseDTO responseDTO;
        try {
            List<User> users = userService.findAllUsers();
            responseDTO = customBuilders.BuildMessageDTO(true,
                    "Lista de usuários encontrados:", mapResponses.mapToUserResponses(users), "" );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDTO);
        }
        catch (Exception e) {
            responseDTO = customBuilders.BuildMessageDTO(true,
                    "Falha ao buscar usuários.", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDTO);

        }
    }
}
