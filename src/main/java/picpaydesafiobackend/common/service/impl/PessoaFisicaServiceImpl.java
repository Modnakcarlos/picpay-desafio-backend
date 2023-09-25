package picpaydesafiobackend.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaFisica;
import picpaydesafiobackend.common.repository.PessoaFisicaRepository;
import picpaydesafiobackend.common.service.PessoaFisicaService;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    public PessoaFisica criarPessoaFisica(User user) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setFullName(user.getUsername());
        pessoaFisica.setUsuarioId(user.getId());

        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public PessoaFisica findPessoaFisicaById(String id) {
        return pessoaFisicaRepository.findById(id).orElse(null);
    }

    public PessoaFisica findPessoaFisicaByUsuarioId(String usuarioId) {
        return pessoaFisicaRepository.findPessoaFisicaByUsuarioId(usuarioId).orElse(null);
    }
}
