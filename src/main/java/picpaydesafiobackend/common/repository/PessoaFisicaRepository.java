package picpaydesafiobackend.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import picpaydesafiobackend.common.entity.PessoaFisica;

import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, String> {

    Optional<PessoaFisica> findPessoaFisicaByUsuarioId(String userId);
}
