package ada.tech.app.models;

import lombok.Getter;
import lombok.Setter;
import java.util.regex.Pattern;


@Setter
@Getter

public class PJ extends Pessoa {
    private String CNPJ;

    public PJ(String nome, String CNPJ) {
        super(nome);
        setCNPJ(CNPJ);
    }

    public void setCNPJ(String CNPJ) {
        CNPJ = CNPJ.replaceAll("[^0-9]", "");

        if (validarCNPJ(CNPJ)) {
            this.CNPJ = CNPJ;
            setIdentificador(CNPJ);
        } else {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    private boolean validarCNPJ(String CNPJ) {
        String regex = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";
        return Pattern.matches(regex, CNPJ);
    }

    @Override
    public String toString() {
        return this.nome + " " + this.CNPJ;
    }
}
