package picpaydesafiobackend.carteira.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TransacaoRequest {
    //private String pagador;
    private String emailRecebedor;
    private Double valor;
}
