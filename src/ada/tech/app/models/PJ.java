package ada.tech.app.models;

import lombok.*;


@Setter
@Getter
public class PJ extends Pessoa{
    private String CNPJ;

    public PJ(String nome, String CNPJ, boolean alugouCarro) {
        super( nome, alugouCarro);
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString(){
        return this.nome + " " + this.CNPJ;
    }

}
