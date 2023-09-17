package picpaydesafiobackend.common.entity;

import picpaydesafiobackend.application.utils.Constants;
import picpaydesafiobackend.authentication.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(schema = Constants.SCHEMA_COMMON, name = "pessoa_fisica")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_pessoa_id"))
public class PessoaFisica extends Pessoa {

    @Column(name = "cpf")
    private String CPF;

    public PessoaFisica(String fullName, User user, String CPF) {
        super(fullName, user);
        this.CPF = CPF;
    }
}
