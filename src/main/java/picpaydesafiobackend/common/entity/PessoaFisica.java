package picpaydesafiobackend.common.entity;

import jakarta.persistence.*;
import lombok.*;
import picpaydesafiobackend.application.utils.Constants;

@Entity
@Table(schema = Constants.SCHEMA_COMMON, name = "pessoa_fisica")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "fk_pessoa_id"))
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PessoaFisica extends Pessoa {


    //private String name;

    @Column(name = "cpf")
    private String cpf;
}
