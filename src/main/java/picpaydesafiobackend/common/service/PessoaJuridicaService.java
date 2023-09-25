package picpaydesafiobackend.common.service;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaJuridica;

public interface PessoaJuridicaService {

    PessoaJuridica criarPessoaJuridica(User user);
    PessoaJuridica findPessoaJuridicaById(String id);

}
