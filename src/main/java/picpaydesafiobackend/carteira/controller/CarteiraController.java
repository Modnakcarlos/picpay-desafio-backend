package picpaydesafiobackend.carteira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.carteira.payload.request.TransacaoRequest;
import picpaydesafiobackend.carteira.payload.request.WalletRequest;
import picpaydesafiobackend.carteira.service.CarteiraService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.utils.MapResponses;
import picpaydesafiobackend.common.utils.MessageResponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.CARTEIRA)
@RequiredArgsConstructor
public class CarteiraController {

    private final CarteiraService carteiraService;
    private final UserService userService;
    private final MessageResponseService messageResponseService;
    private final MapResponses mapResponses;

    @PutMapping("add-money-wallet")
    public ResponseEntity<MessageResponseDTO> addMoneyInWallet(@Valid @RequestBody WalletRequest walletRequest) {
        MessageResponseDTO messageResponseDTO;
        try {
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
    public ResponseEntity<MessageResponseDTO> sendMoney(@Valid @RequestBody TransacaoRequest transacaoRequest) {
        MessageResponseDTO messageResponseDTO;
        try {
            validateTransacao(transacaoRequest);

            User pagador = userService.findUserById(transacaoRequest.getPagador());
            validateUser(pagador);

            User recebedor = userService.findUserById(transacaoRequest.getRecebedor());
            validateUser(recebedor);

            carteiraService.sendMoney(transacaoRequest, pagador, recebedor);

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Transferência realizada com sucesso!", mapResponses.mapToTransacaoResponse(pagador, recebedor, transacaoRequest.getValor()), "");

            return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao realizar transferência", null, e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }

    private void validateTransacao(TransacaoRequest transacaoRequest) throws UserException {
        if (transacaoRequest.getPagador().equals(transacaoRequest.getRecebedor())) {
            throw new UserException("Você não pode mandar dinheiro para si mesmo.");
        }
    }

    private void validateUser(User user) throws UserException {
        if (user == null) {
            throw new UserException("Usuário não encontrado na base de dados.");
        }
    }
}
