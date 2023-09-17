package picpaydesafiobackend.common.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaJuridica;
import picpaydesafiobackend.common.payload.request.PessoaRequest;

public interface PessoaJuridicaService {
    PessoaJuridica createPessoaJuridica(PessoaRequest pessoaRequest, User user);
}
