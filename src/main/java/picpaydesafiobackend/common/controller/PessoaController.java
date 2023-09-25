package picpaydesafiobackend.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import picpaydesafiobackend.application.exceptions.ResourceNotFoundException;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.payload.request.ContatoRequest;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.service.PessoaService;

@RequiredArgsConstructor
@RestController
@RequestMapping(Routes.PESSOA)
@CrossOrigin(origins = "*")
public class PessoaController {
    private final PessoaService pessoaService;
    private final UserService userService;
    private final MessageResponseService messageResponseService;


    private void validateUser(String userId) throws UserException {
        userService.validateUser(userId);
    }

    @Transactional
    @PutMapping("update-pessoa-contacts")
    public ResponseEntity<MessageResponseDTO> updatePessoaContacts(@RequestBody ContatoRequest contatoRequest) {
        MessageResponseDTO responseDTO;
        try {

            User user = userService.findUserById(contatoRequest.getUsuarioId());
            validateUser(user.getId());

            Pessoa pessoa = pessoaService.updatePessoaContacts(contatoRequest, user);

            responseDTO = messageResponseService.prepareMessageBuild(true,
                    "Informação pessoal atualizada com êxito.", null, "");

            return ResponseEntity.ok().body(responseDTO);
        } catch (UserException | ResourceNotFoundException e) {
            responseDTO = messageResponseService.prepareMessageBuild(false,
                    e.getMessage(), null, e.getMessage());

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO = messageResponseService.prepareMessageBuild(false,
                    "Houve uma falha ao atualizar dados de contato de pessoa.", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }
    }
}
