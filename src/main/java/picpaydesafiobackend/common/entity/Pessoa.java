package picpaydesafiobackend.common.entity;

import picpaydesafiobackend.application.utils.Constants;
import picpaydesafiobackend.authentication.entity.User;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(schema = Constants.SCHEMA_COMMON, name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String fullName;


    @NotNull(message = "Usuário é dado obrigatório.")
    @Column(name = "usuario")
    private String usuarioId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pessoa", orphanRemoval = true)
    private List<SocialLink> socialLinks;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pessoa", orphanRemoval = true)
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pessoa", orphanRemoval = true)
    private List<Contato> contacts;
}