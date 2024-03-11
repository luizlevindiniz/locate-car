package ada.tech.app.models;

import lombok.*;

@Getter
@Setter
public abstract class Pessoa {
    protected Integer repositoryID;
    protected String nome;
    protected boolean alugouCarro;
}
