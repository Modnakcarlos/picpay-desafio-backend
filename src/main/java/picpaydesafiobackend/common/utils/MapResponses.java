package picpaydesafiobackend.common.utils;

import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.response.AuthenticatedUserResponse;
import picpaydesafiobackend.authentication.payload.response.UserResponse;
import picpaydesafiobackend.carteira.entity.Cartao;
import picpaydesafiobackend.carteira.payload.response.CartaoResponse;
import picpaydesafiobackend.carteira.payload.response.TransacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.common.entity.Pessoa;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapResponses {

    public CartaoResponse mapToCartaoResponse(Cartao cartao) {
        return CartaoResponse.builder()
                .bandeira(cartao.getBandeira())
                .nomeTitular(cartao.getNomeTitular())
                .numero(cartao.getNumero())
                .build();
    }


    public List<UserResponse> mapToUserResponses(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(mapToUserResponse(user));
        }
        return userResponses;
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dtUltimoLogin(user.getDtUltimoLogin())
                .tipoPessoa(user.getTipoPessoa())
                .build();
    }

    public TransacaoResponse mapToTransacaoResponse(User pagador, User recebedor, Double valor) {
        return TransacaoResponse.builder()
                .pagadorResponse(mapToUserResponse(pagador))
                .recebedorResponse(mapToUserResponse(recebedor))
                .valor(valor)
                .build();
    }

    public AuthenticatedUserResponse mapToAuthenticatedUserResponse(User user, Pessoa pessoa, String token) {
        return AuthenticatedUserResponse.builder()
                .userResponse(mapToUserResponse(user))
                .pessoa(pessoa)
                .token(token)
                .build();
    }
}
