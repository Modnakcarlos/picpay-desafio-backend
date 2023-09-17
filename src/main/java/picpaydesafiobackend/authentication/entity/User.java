package picpaydesafiobackend.authentication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;
import picpaydesafiobackend.carteira.entity.Carteira;
import picpaydesafiobackend.common.entity.Pessoa;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(schema = Constants.SCHEMA_COMMON, name = "usuario")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Email é campo obrigatório.")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Senha é campo obrigatório.")
    @Column(name = "password")
    private String password;

    @Column(name = "tipo_pessoa")
    private String tipoPessoa;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", optional = true)
    private Carteira carteira;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_ultimo_login")
    private LocalDateTime dtUltimoLogin;
}
