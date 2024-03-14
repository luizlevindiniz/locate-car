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
    /* Infelizmente, o lombok não tem uma opcao para escolher quais campos entrar no builder. O builder
     * ira mostrar todos os campos, mesmo que nao queiramos expor o repositoryID. Ignorar que e possivel construir
     * um objeto ja definindo o repositoryID ao inves dele ser automaticamente definido quando adicionado na lista
     * de veiculos.  */
    private Integer repositoryID;
    private String nome;
    private String placa;
    private String marca;
    private boolean alugado = false;

    @Override
    public String toString() {
        return "Veiculo: " + this.nome + " | Marca: " + this.marca + " | Placa: " + this.placa + " | Tipo: " + this.tipo;
    }

}
