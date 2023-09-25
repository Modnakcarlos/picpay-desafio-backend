package picpaydesafiobackend.common.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;

@Entity
@Table(schema = Constants.SCHEMA_COMMON, name = "contato")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_contato")
    private String tipoContato;

    private String value;

    @ManyToOne
    @NotNull(message = "Pessoa é um dado obrigatório.")
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_pessoa_id"), nullable = false)
    private Pessoa pessoa;
}
