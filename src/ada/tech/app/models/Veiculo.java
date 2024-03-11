package ada.tech.app.models;


import lombok.*;

@Data
@Builder
public class Veiculo {
    private Integer repositoryID;

    @NonNull private String placa;

    @NonNull private String nome;

    @NonNull private String marca;

    @NonNull TipoCarro tipo;

    @NonNull private boolean estaAlugado;

    @Override
    public String toString(){
        return "Veiculo " + this.nome + " placa: " + this.placa;
    }

}
