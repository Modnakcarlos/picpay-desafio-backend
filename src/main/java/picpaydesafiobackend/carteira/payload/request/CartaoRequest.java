package picpaydesafiobackend.carteira.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CartaoRequest {

    private String bandeira;
    private String nomeTitular;
    private String numero;
    private LocalDate validade;
    private String codigoSeguranca;
}
