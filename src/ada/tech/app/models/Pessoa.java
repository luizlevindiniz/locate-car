package ada.tech.app.models;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public abstract class Pessoa {
    protected Integer repositoryID;
    protected String nome;
    protected boolean alugouCarro;
    protected String identificador;

    public Pessoa(String nome, boolean alugouCarro) {
        this.nome = nome;
        this.alugouCarro = alugouCarro;
    }

}
