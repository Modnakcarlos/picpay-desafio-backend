package picpaydesafiobackend.common.entity;

import jakarta.persistence.*;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(schema = Constants.SCHEMA_COMMON, name = "social_links")
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String link;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_pessoa_id"), nullable = false)
    private Pessoa pessoa;
}
