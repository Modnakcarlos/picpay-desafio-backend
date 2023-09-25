package picpaydesafiobackend.carteira.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import picpaydesafiobackend.application.exceptions.UserException;
import picpaydesafiobackend.application.exceptions.WalletException;
import picpaydesafiobackend.application.payload.response.MessageResponseDTO;
import picpaydesafiobackend.authentication.entity.User;
import picpaydesafiobackend.authentication.service.UserService;
import picpaydesafiobackend.carteira.entity.Cartao;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.carteira.payload.request.CartaoRequest;
import picpaydesafiobackend.carteira.service.CartaoService;
import picpaydesafiobackend.carteira.service.CarteiraService;
import picpaydesafiobackend.common.routes.Routes;
import picpaydesafiobackend.common.service.MessageResponseService;
import picpaydesafiobackend.common.service.TokenService;
import picpaydesafiobackend.common.utils.MapResponses;

@RestController
@RequestMapping((Routes.CARTAO))
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;
    private final MapResponses mapResponses;
    private final MessageResponseService messageResponseService;
    private final CarteiraService carteiraService;
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("add")
    public ResponseEntity<MessageResponseDTO> addCartaoInCarteira(@RequestBody CartaoRequest cartao, HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {

            tokenService.checkAccessToken(request);

            User user = userService.findUserByEmail(tokenService.extractUserEmail(request));

            Carteira carteira = carteiraService.getCarteiraByUser(user);

            Cartao newCartao = cartaoService.addCartaoInCarteira(cartao, carteira);

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Cartão criado!", mapResponses.mapToCartaoResponse(newCartao), "");

            return ResponseEntity.ok().body(messageResponseDTO);
        }
        catch (Exception e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao buscar dados.", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }

    @GetMapping("get")
    public ResponseEntity<MessageResponseDTO> getCartaoAtCarteira(HttpServletRequest request) {
        MessageResponseDTO messageResponseDTO;
        try {
        tokenService.checkAccessToken(request);

        User user = userService.findUserByEmail(tokenService.extractUserEmail(request));

        Carteira carteira = carteiraService.getCarteiraByUser(user);

            Cartao cartao = cartaoService.findCartaoAtCarteira(carteira.getId());

            messageResponseDTO = messageResponseService.prepareMessageBuild(true,
                    "Cartão encontrado!", mapResponses.mapToCartaoResponse(cartao), "");

            return ResponseEntity.ok().body(messageResponseDTO);
        }
        catch (UserException | WalletException e) {
            messageResponseDTO = messageResponseService.prepareMessageBuild(false,
                    "Falha ao buscar dados.", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponseDTO);
        }
    }

}
