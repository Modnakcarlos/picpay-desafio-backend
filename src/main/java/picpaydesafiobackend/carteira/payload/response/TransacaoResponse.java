package picpaydesafiobackend.carteira.payload.response;

import picpaydesafiobackend.authentication.payload.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransacaoResponse {
    private UserResponse pagadorResponse;
    private UserResponse recebedorResponse;
    private Double valor;
}
