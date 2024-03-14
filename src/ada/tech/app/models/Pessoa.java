package ada.tech.app.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public abstract class Pessoa {
    protected Integer repositoryID;
    protected String nome;
    protected List<Veiculo> veiculosAlugados = new ArrayList<>();
    protected String identificador;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public void registrarVeiculoAlugado(Veiculo veiculo) {
        veiculosAlugados.add(veiculo);
    }

    public void removerVeiculoAlugado(Veiculo veiculo) {
        veiculosAlugados.remove(veiculo);
    }

    public void printaVeiculosAlugados() {
        veiculosAlugados.forEach(System.out::println);
    }

}
