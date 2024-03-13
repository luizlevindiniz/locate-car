package ada.tech.app.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PF extends Pessoa {
    private String CPF;
    public PF(String nome, String CPF, boolean alugouCarro) {
        super( nome, alugouCarro);
        this.CPF = CPF;
    }

    @Override
    public String toString(){
        return this.nome + " " + this.CPF;
    }

}
