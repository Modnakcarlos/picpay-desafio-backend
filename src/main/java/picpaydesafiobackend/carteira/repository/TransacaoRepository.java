package picpaydesafiobackend.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import picpaydesafiobackend.carteira.entity.Transacao;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,  Long> {
    List<Transacao> findAllByPagador(String pagadorId);
}
