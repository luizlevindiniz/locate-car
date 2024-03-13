package ada.tech.app.models;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Veiculo {
    private Integer repositoryID; // o lombok não tem uma opcao de nao setar esse cara

    private String nome;

    private String placa;

    private String marca;

    TipoCarro tipo;

    private boolean estaAlugado;

    @Override
    public String toString() {
        return "Veiculo: " + this.nome + " | Marca: " + this.marca + " | Placa: " + this.placa + " | Tipo: " + this.tipo;
    }

}
