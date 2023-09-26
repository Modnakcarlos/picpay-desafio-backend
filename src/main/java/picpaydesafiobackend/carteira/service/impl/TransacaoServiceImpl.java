package picpaydesafiobackend.carteira.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.carteira.entity.Transacao;
import picpaydesafiobackend.carteira.repository.TransacaoRepository;
import picpaydesafiobackend.carteira.service.TransacaoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public List<Transacao> findAllTransacoesByUser(String userId) {
        return transacaoRepository.findAllByPagador(userId);
    }

    public List<Transacao> findAllTransacoes() {
        return transacaoRepository.findAll();
    }

    public void addTransacaoToRecentIfIsValid(Transacao transacao) {

        List<Transacao> allTransacoes = findAllTransacoes();

        allTransacoes.add(transacao);
        transacaoRepository.saveAll(allTransacoes);
    }
}
