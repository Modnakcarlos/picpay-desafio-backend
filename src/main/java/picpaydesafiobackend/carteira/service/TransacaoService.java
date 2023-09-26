package picpaydesafiobackend.carteira.service;

import picpaydesafiobackend.carteira.entity.Transacao;

import java.util.List;

public interface TransacaoService {
    List<Transacao> findAllTransacoesByUser(String userId);
    List<Transacao> findAllTransacoes();
    void addTransacaoToRecentIfIsValid(Transacao transacaoResponse);

}
