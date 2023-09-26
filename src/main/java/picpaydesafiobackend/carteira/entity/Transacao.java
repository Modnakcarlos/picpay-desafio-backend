package picpaydesafiobackend.carteira.entity;

import jakarta.persistence.*;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(schema = Constants.SCHEMA_COMMON, name = "transacao")
public class Transacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pagador")
    private String pagador;

    @Column(name = "recebedor")
    private String recebedor;

    @Column(name = "valor")
    private Double valor;
}
