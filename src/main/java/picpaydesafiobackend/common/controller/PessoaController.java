package picpaydesafiobackend.common.controller;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
