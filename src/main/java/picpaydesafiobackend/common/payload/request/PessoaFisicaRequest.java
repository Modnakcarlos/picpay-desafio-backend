package picpaydesafiobackend.common.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PessoaFisicaRequest {
    private String fullName;
    private String userId;
    private String CPF;
}
