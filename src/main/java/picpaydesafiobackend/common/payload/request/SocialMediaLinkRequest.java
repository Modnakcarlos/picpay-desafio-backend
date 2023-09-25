package picpaydesafiobackend.common.payload.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialMediaLinkRequest {
    private String nome;
    private String link;
}
