package picpaydesafiobackend.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import picpaydesafiobackend.common.entity.Pessoa;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findPessoaByUsuarioId(String usuarioId);
}
