package picpaydesafiobackend.common.utils;

import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.payload.response.AuthenticatedUserResponse;
import picpaydesafiobackend.authentication.payload.response.UserResponse;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.carteira.entity.Cartao;
import picpaydesafiobackend.carteira.entity.Transacao;
import picpaydesafiobackend.carteira.payload.response.CartaoResponse;
import picpaydesafiobackend.carteira.payload.response.TransacaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.common.entity.Pessoa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MapResponses {

    private final UserService userService;

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

    public Set<UserResponse> mapToUserFrequentesResponses(Set<User> users) {
        Set<UserResponse> userResponses = new HashSet<>();
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

    public List<TransacaoResponse> mapToTransacoesResponses(List<Transacao> transacoes) throws UserException {
        List<TransacaoResponse> transacaoResponses = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            transacaoResponses.add(mapToTransacaoResponse(transacao));
        }
        return transacaoResponses;
    }

    public TransacaoResponse mapToTransacaoResponse(Transacao transacao) throws UserException {
        User pagador = userService.findUserById(transacao.getPagador());
        User recebedor = userService.findUserById(transacao.getRecebedor());
        return TransacaoResponse.builder()
                .pagadorResponse(mapToUserResponse(pagador))
                .recebedorResponse(mapToUserResponse(recebedor))
                .valor(transacao.getValor())
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
