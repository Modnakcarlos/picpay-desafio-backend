package picpaydesafiobackend.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.common.entity.PessoaJuridica;
import picpaydesafiobackend.common.repository.PessoaJuridicaRepository;
import picpaydesafiobackend.common.service.PessoaJuridicaService;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridica criarPessoaJuridica(User user) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setFullName(user.getUsername());
        pessoaJuridica.setUsuarioId(user.getId());

        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public PessoaJuridica findPessoaJuridicaById(String id) {
        return pessoaJuridicaRepository.findById(id).orElse(null);
    }

    public PessoaJuridica findPessoaJuridicaByUsuarioId(String usuarioId) {
        return pessoaJuridicaRepository.findPessoaJuridicaByUsuarioId(usuarioId).orElse(null);
    }


}
