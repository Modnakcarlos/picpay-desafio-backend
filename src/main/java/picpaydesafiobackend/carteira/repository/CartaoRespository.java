package picpaydesafiobackend.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import picpaydesafiobackend.carteira.entity.Cartao;

import java.util.Optional;

@Repository
public interface CartaoRespository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findCartaoByCarteiraId(Integer carteiraId);
}
