package picpaydesafiobackend.common.service;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.entity.PessoaFisica;
import picpaydesafiobackend.common.entity.PessoaJuridica;
import picpaydesafiobackend.common.payload.request.ContatoRequest;

public interface PessoaService {
    void createPessoa(User user) throws UserException;
    Pessoa updatePessoaContacts(ContatoRequest contatoRequest, User user) throws UserException;

    PessoaFisica findPessoaFisicaById(String id);
    PessoaJuridica findPessoaJuridicaById(String id);



    PessoaFisica findPessoaFisicaByUsuarioId(String usuarioId);
    PessoaJuridica findPessoaJuridicaByUsuarioId(String usuarioId);


}
