package picpaydesafiobackend.carteira.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.application.exceptions.WalletException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.payload.request.TransacaoRequest;
import picpaydesafiobackend.carteira.payload.request.WalletRequest;
import picpaydesafiobackend.carteira.repository.CarteiraRepository;
import picpaydesafiobackend.carteira.service.CarteiraService;
import picpaydesafiobackend.common.utils.TipoPessoa;

@Service
@RequiredArgsConstructor
public class CarteiraServiceImpl implements CarteiraService {
    private final CarteiraRepository carteiraRepository;

    @Override
    public Carteira getCarteiraByUser(User user) throws WalletException {
        return carteiraRepository.findCarteiraByUser(user)
                .orElseThrow(() -> new WalletException("Falha ao buscar dados de carteira."));


    }

    @Override
    public String getSaldoByUser(User user) throws WalletException {
        Carteira carteira = carteiraRepository.findCarteiraByUser(user)
                .orElseThrow(() -> new WalletException("Falha ao buscar dados de carteira."));

        return String.format("%.2f", carteira.getSaldo());
    }

    @Override
    public Carteira addMoney(WalletRequest walletRequest, User user) {
        Carteira carteira = user.getCarteira();

        Double saldoCarteira = carteira.getSaldo();

        carteira.setSaldo(saldoCarteira + walletRequest.getValor());
        return carteiraRepository.save(carteira);
    }

    @Override
    public void sendMoney(TransacaoRequest transacaoRequest, User pagador, User recebedor) throws WalletException {
        validateTipoPessoa(pagador);
        checkIfExistsBalance(pagador, transacaoRequest.getValor());

        validateAndReductAmountFromPagadorWallet(pagador, transacaoRequest.getValor());
        addToCarteiraRecebedor(recebedor, transacaoRequest.getValor());
    }

    public void salveCarteira(Carteira carteira) {
        carteiraRepository.save(carteira);
    }

    private void addToCarteiraRecebedor(User recebedor, Double valor) throws WalletException {
        validateTipoPessoa(recebedor);

        Carteira carteiraRecebedor = recebedor.getCarteira();

        carteiraRecebedor.setSaldo(carteiraRecebedor.getSaldo() + valor);
        carteiraRepository.save(carteiraRecebedor);
    }

    private void validateAndReductAmountFromPagadorWallet(User pagador, Double valor) throws WalletException {
        validateTipoPessoa(pagador);
        checkIfExistsBalance(pagador, valor);

        Carteira carteiraPagador = pagador.getCarteira();

        carteiraPagador.setSaldo(carteiraPagador.getSaldo() - valor);
        carteiraRepository.save(carteiraPagador);
    }

    private void validateTipoPessoa(User user) throws WalletException {
        if (!user.getTipoPessoa().equals(TipoPessoa.FISICA.getAbbreviation())) {
            throw new WalletException("Lojistas não podem enviar dinheiro, apenas usuários.");
        }
    }

    private void checkIfExistsBalance(User user, Double value) throws WalletException {
        Carteira carteira = user.getCarteira();


        if (carteira.getSaldo() < value) {
            throw new WalletException("Saldo insuficiente.");
        }
    }
}
