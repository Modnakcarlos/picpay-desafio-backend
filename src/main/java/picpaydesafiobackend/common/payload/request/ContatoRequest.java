package picpaydesafiobackend.common.payload.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContatoRequest {
    private EnderecoRequest endereco;

    private List<SocialMediaLinkRequest> socialMediaLinks;

    private List<ContactNumberRequest> contacts;

    private Long pessoaId;

    private String usuarioId;
}
