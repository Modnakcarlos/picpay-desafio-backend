package picpaydesafiobackend.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import picpaydesafiobackend.common.entity.PessoaJuridica;

import java.util.Optional;

@Repository
public interface PessoaJuridicaRepository  extends JpaRepository<PessoaJuridica, String> {

    Optional<PessoaJuridica> findPessoaJuridicaByUsuarioId(String usuarioId);
}
