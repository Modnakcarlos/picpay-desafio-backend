package picpaydesafiobackend.common.service;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.payload.request.PessoaRequest;

public interface PessoaService {
    Pessoa createPessoa(PessoaRequest pessoaRequest, User user);
}
