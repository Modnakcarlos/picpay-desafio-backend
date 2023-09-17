package picpaydesafiobackend.authentication.payload.request;

import picpaydesafiobackend.common.payload.request.PessoaRequest;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String username;
    private String tipoPessoa;
    private PessoaRequest pessoa;
}
