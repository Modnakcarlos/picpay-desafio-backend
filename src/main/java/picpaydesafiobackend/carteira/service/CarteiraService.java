package picpaydesafiobackend.carteira.service;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.application.exceptions.WalletException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.payload.request.TransacaoRequest;
import picpaydesafiobackend.carteira.payload.request.WalletRequest;

public interface CarteiraService {
    Carteira addMoney(WalletRequest walletRequest, User user) throws UserException;
    void sendMoney(TransacaoRequest transacaoRequest, User pagador, User recebedor) throws WalletException;
}
