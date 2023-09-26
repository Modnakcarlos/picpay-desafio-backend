package picpaydesafiobackend.carteira.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.carteira.entity.Transacao;
import picpaydesafiobackend.carteira.service.TransacaoService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.service.TokenService;
import picpaydesafiobackend.common.utils.MapResponses;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(Routes.TRANSACAO)
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class TransacaoController {


    private final TransacaoService transacaoService;
    private final UserService userService;
    private final MessageResponseService messageResponseService;
    private final TokenService tokenService;
    private final MapResponses mapResponses;

    @GetMapping("frequentes")
    public ResponseEntity<MessageResponseDTO> getFrequentesByUserEmail(HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {

            tokenService.checkAccessToken(request);
            User pagador = userService.findUserByEmail(tokenService.extractUserEmail(request));

            List<Transacao> transacoes = transacaoService.findAllTransacoesByUser(pagador.getId());

            Set<User> usuarioFrequente = new HashSet<>();

            for (Transacao transacaoRecente : transacoes) {
                User user = userService.findUserById(transacaoRecente.getRecebedor());
                usuarioFrequente.add(user);
            }

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Busca realizada com sucesso!", mapResponses.mapToUserFrequentesResponses(usuarioFrequente), "");

            return ResponseEntity.ok().body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao buscar dados.", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }

    @GetMapping("get-transacoes")
    public ResponseEntity<MessageResponseDTO> findAllTransacoesByUser(HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {

            tokenService.checkAccessToken(request);

            User user = userService.findUserByEmail(tokenService.extractUserEmail(request));

            List<Transacao> transacoes = transacaoService.findAllTransacoesByUser(user.getId());

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Busca realizada com sucesso!", mapResponses.mapToTransacoesResponses(transacoes), "");

            return ResponseEntity.ok().body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao buscar dados.", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }
}
