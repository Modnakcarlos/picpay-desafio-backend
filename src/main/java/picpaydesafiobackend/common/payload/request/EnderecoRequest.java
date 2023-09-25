package picpaydesafiobackend.common.payload.request;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoRequest {

    private String bairro;
    private String complemento;
    private String logradouro;

    @Column(name = "aux_logradouro")
    private String auxLogradouro;

    private String numero;
    private String cep;
    private String cidade;
    private String uf;
    private String pais;
}
