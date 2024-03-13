package ada.tech.app.models;

import lombok.*;



@Setter
@Getter
public abstract class Pessoa {
    protected Integer repositoryID;
    protected String nome;
    protected boolean alugouCarro;

    public Pessoa(String nome, boolean alugouCarro) {
        this.nome = nome;
        this.alugouCarro = alugouCarro;
    }

}
