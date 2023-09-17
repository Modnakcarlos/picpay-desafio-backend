package picpaydesafiobackend.carteira.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import picpaydesafiobackend.authentication.payload.response.UserResponse;

@Getter
@Setter
@Builder
public class TransacaoResponse {
    private UserResponse pagadorResponse;
    private UserResponse recebedorResponse;
    private Double valor;
}
