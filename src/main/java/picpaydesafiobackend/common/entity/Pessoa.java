package picpaydesafiobackend.common.entity;

import jakarta.persistence.*;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;
import picpaydesafiobackend.authentication.entity.User;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = Constants.SCHEMA_COMMON, name = "pessoa")
public abstract class Pessoa implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String fullName;

    @OneToOne
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "fk_user_id"), nullable = false)
    @NotNull(message = "Usuário é dado obrigatório.")
    private User user;

    public Pessoa(String fullName, User user) {
        this.fullName = fullName;
        this.user = user;
    }
}