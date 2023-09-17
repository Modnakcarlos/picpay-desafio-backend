package picpaydesafiobackend.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.utils.CustomBuilders;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(Routes.PESSOA)
public class PessoaController {
    private final PessoaService pessoaService;
    private final UserService userService;
    private final CustomBuilders customBuilders;
/*

    @PostMapping("criar-pessoa")
    public ResponseEntity<MessageResponseDTO> createPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
        MessageResponseDTO messageResponseDTO;
        try {
            User user = userService.findUserById(pessoaRequest.getUserId());

            pessoaService.createPessoa(pessoaRequest, user);

            messageResponseDTO = customBuilders.BuildMessageDTO(true,
                    "Pessoa criada com êxito.", null, "");

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = customBuilders.BuildMessageDTO(false,
                    "Falha ao criar pessoa.", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(messageResponseDTO);
        }
    }
*/

    private void validateUser(String userId) throws UserException {
        userService.validateUser(userId);
    }
}
