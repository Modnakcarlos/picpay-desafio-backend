package picpaydesafiobackend.carteira.entity;

import picpaydesafiobackend.application.utils.Constants;
import picpaydesafiobackend.authentication.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(schema = Constants.SCHEMA_COMMON, name = "carteira")
public class Carteira implements Serializable {

    private static final long versionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "saldo")
    private Double saldo;

    @OneToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_usuario_id"))
    private User user;
}
