package picpaydesafiobackend.common.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PessoaRequest {
    private String fullName;
    private String document;
}
