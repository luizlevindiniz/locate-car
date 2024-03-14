package ada.tech.app.models;

import lombok.Getter;
import lombok.Setter;


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
        return CNPJ.matches("\\d{14}") && !CNPJ.matches("(\\d)\\1{13}");
    }

    @Override
    public String toString() {
        return this.nome + " " + this.CNPJ;
    }

}
