package picpaydesafiobackend.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.Pessoa;
import picpaydesafiobackend.common.payload.request.PessoaRequest;
import picpaydesafiobackend.common.service.PessoaFisicaService;
import picpaydesafiobackend.common.service.PessoaJuridicaService;
import picpaydesafiobackend.common.service.PessoaService;
import picpaydesafiobackend.common.utils.TipoPessoa;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private final PessoaFisicaService pessoaFisicaService;
    private final PessoaJuridicaService pessoaJuridicaService;

    public Pessoa createPessoa(PessoaRequest pessoaRequest, User user) {
        Pessoa pessoa;
        if(user.getTipoPessoa().equals(TipoPessoa.FISICA.getAbbreviation())) {
            pessoa = pessoaFisicaService.createPessoaFisica(pessoaRequest, user);
        }
        else {
            pessoa = pessoaJuridicaService.createPessoaJuridica(pessoaRequest, user);
        }
        return pessoa;
    }
}
