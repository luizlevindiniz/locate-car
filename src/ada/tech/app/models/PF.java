package ada.tech.app.models;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PF extends Pessoa {
    private String CPF;

    public PF(String nome, String CPF) {
        super(nome);
        setCPF(CPF);
    }

    public void setCPF(String CPF) {
        CPF = CPF.replaceAll("[^0-9]", "");

        if (validarCPF(CPF)) {
            this.CPF = CPF;
            setIdentificador(CPF);
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private boolean validarCPF(String CPF) {
        CPF = CPF.replaceAll("[^0-9]", "");


        if (CPF.length() != 11)
            return false;

        if (CPF.matches("(\\d)\\1{10}"))
            return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (CPF.charAt(i) - '0') * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        if ((CPF.charAt(9) - '0') != digitoVerificador1)
            return false;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (CPF.charAt(i) - '0') * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        return (CPF.charAt(10) - '0') == digitoVerificador2;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.CPF;
    }

}