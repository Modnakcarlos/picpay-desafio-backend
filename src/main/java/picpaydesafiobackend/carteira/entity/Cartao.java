package picpaydesafiobackend.carteira.entity;

import jakarta.persistence.*;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(schema = Constants.SCHEMA_COMMON, name = "cartao")
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bandeira")
    private String bandeira;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "numero")
    private String numero;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "codigo_seguranca")
    private String codigoSeguranca;

    @Column(name = "carteira_id")
    private Integer carteiraId;

}
