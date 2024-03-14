package ada.tech.app.repositories.api;

import ada.tech.app.models.Veiculo;

import java.util.List;

public interface VeiculoRepository extends Repository<Veiculo> {
    List<Veiculo> procurarPorNome(String nome);

}
