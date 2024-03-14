package ada.tech.app.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Veiculo {
    TipoVeiculo tipo;
    private Integer repositoryID; // o lombok não tem uma opcao de nao setar esse cara
    private String nome;
    private String placa;
    private String marca;
    private boolean alugado;

    @Override
    public String toString() {
        return "Veiculo: " + this.nome + " | Marca: " + this.marca + " | Placa: " + this.placa + " | Tipo: " + this.tipo;
    }

}
