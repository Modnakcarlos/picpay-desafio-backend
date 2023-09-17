package picpaydesafiobackend.common.service;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaFisica;
import picpaydesafiobackend.common.payload.request.PessoaRequest;

public interface PessoaFisicaService {
    PessoaFisica createPessoaFisica(PessoaRequest pessoaFisicaRequest, User user);
}
