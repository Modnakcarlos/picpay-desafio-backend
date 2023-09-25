package picpaydesafiobackend.common.service.impl;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.entity.PessoaFisica;
import picpaydesafiobackend.common.entity.PessoaJuridica;
import picpaydesafiobackend.common.payload.request.ContatoRequest;
import picpaydesafiobackend.common.repository.PessoaRepository;
import picpaydesafiobackend.common.service.PessoaFisicaService;
import picpaydesafiobackend.common.service.PessoaJuridicaService;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.common.utils.TipoPessoa;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaService pessoaFisicaService;
    private final PessoaJuridicaService pessoaJuridicaService;
    private final CustomBuilders customBuilders;

    public void createPessoa(User user) throws UserException {

        try {
            if(user.getTipoPessoa().equals(TipoPessoa.FISICA.getAbbreviation())) {
                criarPessoaFisica(user);
            }
            else criarPessoaJuridica(user);

        } catch (Exception e) {
            throw new UserException("Falha na criação da entidade de pessoa. Por favor, tente de novo mais tarde.");
        }
    }

    public PessoaFisica criarPessoaFisica(User user) {
        return pessoaFisicaService.criarPessoaFisica(user);
    }

    public PessoaJuridica criarPessoaJuridica(User user) {
        return pessoaJuridicaService.criarPessoaJuridica(user);
    }

    public PessoaFisica findPessoaFisicaById(String id) {
        return pessoaFisicaService.findPessoaFisicaById(id);
    }

    public PessoaJuridica findPessoaJuridicaById(String id) {
        return pessoaJuridicaService.findPessoaJuridicaById(id);
    }

    public PessoaFisica findPessoaFisicaByUsuarioId(String usuarioId) {
        return pessoaFisicaService.findPessoaFisicaByUsuarioId(usuarioId);
    }

    public PessoaJuridica findPessoaJuridicaByUsuarioId(String usuarioId) {
        return pessoaJuridicaService.findPessoaJuridicaById(usuarioId);
    }

    public Pessoa updatePessoaContacts(ContatoRequest contatoRequest, User user) throws UserException {

        try {
            Pessoa pessoa = pessoaRepository.findPessoaByUsuarioId(user.getId())
                    .orElseThrow(() ->  new UserException("Pessoa não encontrada."));

            customBuilders.buildAllContactsAndSetPessoa(contatoRequest, pessoa);
            return pessoaRepository.save(pessoa);
        }
        catch (UserException e) {
            throw new UserException("Falha ao atualizar dados de contato.");
        }

    }
}
