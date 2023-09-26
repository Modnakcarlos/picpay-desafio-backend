package picpaydesafiobackend.carteira.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.application.exceptions.WalletException;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.carteira.entity.Transacao;
import picpaydesafiobackend.carteira.payload.request.TransacaoRequest;
import picpaydesafiobackend.carteira.payload.request.WalletRequest;
import picpaydesafiobackend.carteira.service.CarteiraService;
import picpaydesafiobackend.carteira.service.TransacaoService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.service.TokenService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import picpaydesafiobackend.common.utils.MapResponses;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.CARTEIRA)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CarteiraController {

    private final CarteiraService carteiraService;
    private final UserService userService;
    private final MessageResponseService messageResponseService;
    private final MapResponses mapResponses;
    private final TokenService tokenService;
    private final CustomBuilders customBuilders;
    private final TransacaoService transacaoService;

    @GetMapping("saldo")
    public ResponseEntity<MessageResponseDTO> getSaldoByUserEmail(HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {

            tokenService.checkAccessToken(request);
            User user = userService.findUserByEmail(tokenService.extractUserEmail(request));

            String saldo = carteiraService.getSaldoByUser(user);

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Busca realizada com sucesso!", saldo, "");

            return ResponseEntity.ok().body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao buscar dados.", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }
    @PutMapping("add-money-wallet")
    public ResponseEntity<MessageResponseDTO> addMoneyInWallet(@Valid @RequestBody WalletRequest walletRequest, HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {

            tokenService.checkAccessToken(request);

            User user = userService.findUserById(walletRequest.getUserId());
            validateUser(user);

            carteiraService.addMoney(walletRequest, user);

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Valor adicionado com sucesso!", null, "");

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao adicionar valor na carteira!", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }

    @PutMapping("transferir-dinheiro")
    public ResponseEntity<MessageResponseDTO> sendMoney(@Valid @RequestBody TransacaoRequest transacaoRequest, HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {

            tokenService.checkAccessToken(request);

            User pagador = userService.findUserByEmail(tokenService.extractUserEmail(request));
            User recebedor = userService.findUserByEmail(transacaoRequest.getEmailRecebedor());
            validateTransacao(transacaoRequest, pagador);

            carteiraService.sendMoney(transacaoRequest, pagador, recebedor);

            Transacao transacao = customBuilders.buildTransacao(pagador.getId(), recebedor.getId(), transacaoRequest.getValor());

            transacaoService.addTransacaoToRecentIfIsValid(transacao);

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Transferência realizada com sucesso!", mapResponses.mapToTransacaoResponse(transacao), "");

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }
        catch (UserException | WalletException e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    e.getMessage(), null, e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao realizar transferência", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }

    private void validateTransacao(TransacaoRequest transacaoRequest, User pagador) throws UserException, WalletException {

        if (pagador == null ||
                transacaoRequest.getEmailRecebedor().isEmpty() ||
                transacaoRequest.getValor() == null
        ) {
            throw new UserException("Todos os campos precisam ser preenchidos.");
        }

        if (pagador.getEmail().equals(transacaoRequest.getEmailRecebedor())) {
            throw new UserException("Você não pode mandar dinheiro para si mesmo.");
        }

        if (transacaoRequest.getValor() < 0.01) {
            throw new WalletException("Você precisa inserir um valor válido para transferência, que seja superior a 1 centavo");
        }

        User recebedor = userService.findUserByEmail(transacaoRequest.getEmailRecebedor());
        validateUser(recebedor);
        validateUser(pagador);
    }

    private void validateUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("Usuário não encontrado na base de dados.");
        }
    }
}
