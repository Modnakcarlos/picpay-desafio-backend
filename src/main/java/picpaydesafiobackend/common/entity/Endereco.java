package picpaydesafiobackend.common.entity;

import jakarta.persistence.*;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;

@Entity
@Table(schema = Constants.SCHEMA_COMMON, name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bairro;
    private String complemento;
    private String logradouro;


    private String numero;
    private String cep;
    private String cidade;
    private String uf;
    private String pais;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_pessoa_id"), nullable = false)
    private Pessoa pessoa;
}
