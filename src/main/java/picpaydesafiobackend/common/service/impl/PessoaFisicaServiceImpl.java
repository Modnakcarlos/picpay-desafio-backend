package picpaydesafiobackend.common.service.impl;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaFisica;
import picpaydesafiobackend.common.payload.request.PessoaRequest;
import picpaydesafiobackend.common.service.PessoaFisicaService;
import picpaydesafiobackend.common.utils.CustomBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    private final CustomBuilders customBuilders;
    public PessoaFisica createPessoaFisica(PessoaRequest pessoaFisicaRequest, User user) {
        return customBuilders.buildPessoaFisica(pessoaFisicaRequest, user);
    }
}
