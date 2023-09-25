package picpaydesafiobackend.common.service;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaFisica;

public interface PessoaFisicaService {

    PessoaFisica criarPessoaFisica(User user);
    PessoaFisica findPessoaFisicaById(String id);
    PessoaFisica findPessoaFisicaByUsuarioId(String usuarioId);
}
