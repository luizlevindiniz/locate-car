package ada.tech.app.models;

import lombok.*;


@Setter
@Getter
public class PJ extends Pessoa{
    private String CNPJ;

    public void setCNPJ(String CNPJ) {
        CNPJ = CNPJ.replaceAll("[^0-9]", "");

        if (validarCNPJ(CNPJ)) {
            this.CNPJ = CNPJ;
        } else {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    private boolean validarCNPJ(String CNPJ) {
        return CNPJ.matches("\\d{14}") && !CNPJ.matches("(\\d)\\1{13}");
    }
    public PJ(String nome, String CNPJ, boolean alugouCarro) {
        super( nome, alugouCarro);
        setCNPJ(CNPJ);
    }

    @Override
    public String toString(){
        return this.nome + " " + this.CNPJ;
    }

}
