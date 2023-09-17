package picpaydesafiobackend.common.utils;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public enum TipoPessoa {

    FISICA("Pessoa Física", "PF"),

    JURIDICA("Pessoa Jurídica", "PJ");

    private final String label;

    private final String abbreviation;

    TipoPessoa(String label, String abbreviation) {
        this.label = label;
        this.abbreviation = abbreviation;
    }

    public boolean isPessoaFisica() {
        return Objects.equals(FISICA, this);
    }

    public boolean isPessoaJuridica() {
        return Objects.equals(JURIDICA, this);
    }

    public static List<TipoPessoa> showList() {
        return Arrays.asList(TipoPessoa.values());
    }
}
