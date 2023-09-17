package picpaydesafiobackend.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.request.UserRequest;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.common.entity.PessoaFisica;
import picpaydesafiobackend.common.entity.PessoaJuridica;
import picpaydesafiobackend.common.payload.request.PessoaRequest;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomBuilders {
    public User buildUser(UserRequest userRequest, String encryptedPassword) {
        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encryptedPassword)
                .tipoPessoa(userRequest.getTipoPessoa())
                .dtUltimoLogin(null)
                .build();
    }

    public MessageResponseDTO BuildMessageDTO(Boolean success, String message, Object data, String error) {
        return MessageResponseDTO.builder()
                .success(success)
                .message(message)
                .data(data)
                .error(error)
                .timestamp(LocalDate.now())
                .build();
    }

    public PessoaFisica buildPessoaFisica(PessoaRequest pessoaRequest, User user) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCPF(pessoaRequest.getDocument());
        return pessoaFisica;
    }

    public Carteira buildCarteira(User user) {
        return Carteira.builder()
                .user(user)
                .saldo(0.0)
                .build();
    }

    public PessoaJuridica buildPessoaJuridica(PessoaRequest pessoaRequest, User user) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCNPJ(pessoaRequest.getDocument());
        return pessoaJuridica;
    }
}