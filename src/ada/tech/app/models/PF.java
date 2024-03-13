package ada.tech.app.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PF extends Pessoa {
    private String CPF;
    public void setCPF(String CPF) {
        CPF = CPF.replaceAll("[^0-9]", "");

        if (validarCPF(CPF)) {
            this.CPF = CPF;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }
    private boolean validarCPF(String CPF) {
        return CPF.matches("\\d{11}") && !CPF.matches("(\\d)\\1{10}");
    }
    public PF(String nome, String CPF, boolean alugouCarro) {
        super( nome, alugouCarro);
        setCPF(CPF);
    }

    @Override
    public String toString(){
        return this.nome + " " + this.CPF;
    }

}
