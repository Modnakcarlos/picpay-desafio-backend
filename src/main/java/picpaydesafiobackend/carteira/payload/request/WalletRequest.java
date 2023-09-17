package picpaydesafiobackend.carteira.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WalletRequest {
    private String userId;
    private Double valor;
}
