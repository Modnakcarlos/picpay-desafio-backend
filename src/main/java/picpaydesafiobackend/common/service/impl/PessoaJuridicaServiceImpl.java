package picpaydesafiobackend.common.service.impl;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaJuridica;
import picpaydesafiobackend.common.payload.request.PessoaRequest;
import picpaydesafiobackend.common.service.PessoaJuridicaService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {
    private final CustomBuilders customBuilders;

    public PessoaJuridica createPessoaJuridica(PessoaRequest pessoaRequest, User user) {
        return customBuilders.buildPessoaJuridica(pessoaRequest, user);
    }
}
