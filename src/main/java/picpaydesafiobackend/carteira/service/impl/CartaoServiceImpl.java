package picpaydesafiobackend.carteira.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picpaydesafiobackend.carteira.entity.Cartao;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.payload.request.CartaoRequest;
import picpaydesafiobackend.carteira.repository.CartaoRespository;
import picpaydesafiobackend.carteira.service.CartaoService;
import picpaydesafiobackend.common.utils.CustomBuilders;

@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CustomBuilders customBuilders;
    private final CartaoRespository cartaoRespository;

    public Cartao addCartaoInCarteira(CartaoRequest cartaoRequest, Carteira carteira) {
        Cartao cartao = customBuilders.buildCartao(cartaoRequest, carteira);
        return cartaoRespository.save(cartao);
    }

    public Cartao findCartaoAtCarteira(Integer carteiraId) {
        return cartaoRespository.findCartaoByCarteiraId(carteiraId)
                .orElse(null);
    }
}
