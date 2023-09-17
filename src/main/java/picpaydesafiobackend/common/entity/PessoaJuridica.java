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
@Table(schema = Constants.SCHEMA_COMMON, name = "pessoa_juridica")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_pessoa_id"))
public class PessoaJuridica extends Pessoa{

    @Column(name = "cnpj")
    private String CNPJ;

    public PessoaJuridica(String fullName, User user, String CNPJ) {
        super(fullName, user);
        this.CNPJ = CNPJ;
    }
}