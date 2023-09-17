package picpaydesafiobackend.carteira.service.impl;

import picpaydesafiobackend.application.exceptions.WalletException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.payload.request.TransacaoRequest;
import picpaydesafiobackend.carteira.payload.request.WalletRequest;
import picpaydesafiobackend.carteira.repository.CarteiraRepository;
import picpaydesafiobackend.carteira.service.CarteiraService;
import picpaydesafiobackend.common.utils.TipoPessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraServiceImpl implements CarteiraService {
    private final CarteiraRepository carteiraRepository;

    @Override
    public Carteira addMoney(WalletRequest walletRequest, User user) {
        Double saldoCarteira = user.getCarteira().getSaldo();
        user.getCarteira().setSaldo(saldoCarteira + walletRequest.getValor());
        return carteiraRepository.save(user.getCarteira());
    }

    @Override
    public void sendMoney(TransacaoRequest transacaoRequest, User pagador, User recebedor) throws WalletException {
        validateTipoPessoa(pagador);
        checkIfExistsBalance(pagador, transacaoRequest.getValor());

        Double saldoAtualPagador = pagador.getCarteira().getSaldo();
        Double saldoAtualRecebedor = recebedor.getCarteira().getSaldo();

        pagador.getCarteira().setSaldo(saldoAtualPagador - transacaoRequest.getValor());
        recebedor.getCarteira().setSaldo(saldoAtualRecebedor + transacaoRequest.getValor());

        carteiraRepository.save(pagador.getCarteira());
        carteiraRepository.save(recebedor.getCarteira());
    }

    private void validateTipoPessoa(User user) throws WalletException {
        if (!user.getTipoPessoa().equals(TipoPessoa.FISICA.getAbbreviation())) {
            throw new WalletException("Lojistas não podem enviar dinheiro, apenas usuários.");
        }
    }

    private void checkIfExistsBalance(User user, Double value) throws WalletException {
        if (user.getCarteira().getSaldo() < value) {
            throw new WalletException("Saldo insuficiente.");
        }
    }
}
