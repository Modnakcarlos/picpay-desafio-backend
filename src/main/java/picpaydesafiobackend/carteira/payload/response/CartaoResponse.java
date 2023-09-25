package picpaydesafiobackend.carteira.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartaoResponse {
    private String bandeira;
    private String nomeTitular;
    private String numero;
}
