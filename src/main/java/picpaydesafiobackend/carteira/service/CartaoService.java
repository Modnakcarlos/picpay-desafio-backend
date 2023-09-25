package picpaydesafiobackend.carteira.service;

import picpaydesafiobackend.carteira.entity.Cartao;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.payload.request.CartaoRequest;

public interface CartaoService {

    Cartao addCartaoInCarteira(CartaoRequest cartaoRequest, Carteira carteira);
    Cartao findCartaoAtCarteira(Integer carteiraId);
}
