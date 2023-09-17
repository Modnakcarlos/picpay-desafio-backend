package picpaydesafiobackend.authentication.payload.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {
    private String id;
    private String email;
    private LocalDateTime dtUltimoLogin;
    private String tipoPessoa;
}
