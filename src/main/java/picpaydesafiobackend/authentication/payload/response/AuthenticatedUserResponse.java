package picpaydesafiobackend.authentication.payload.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticatedUserResponse {
    private UserResponse userResponse;
    private Object pessoa;
    private String token;
}
