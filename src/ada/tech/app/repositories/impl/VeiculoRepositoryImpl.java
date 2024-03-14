package ada.tech.app.repositories.impl;

import ada.tech.app.models.Veiculo;
import ada.tech.app.repositories.api.VeiculoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepositoryImpl implements VeiculoRepository {

    private final List<Veiculo> veiculosCadastrados = new ArrayList<>();


    @Override
    public void adicionar(Veiculo veiculo) {
        boolean veiculoJaExiste = procuparPorIdentificador(veiculo.getPlaca()).isEmpty();
        if (!veiculoJaExiste) {
            throw new RuntimeException("Veiculo ja cadastrado!");
        }

        if (veiculo.getRepositoryID() == null) {
            veiculo.setRepositoryID(veiculosCadastrados.size());

            veiculosCadastrados.add(veiculo);
        } else {
            Veiculo substituir = veiculosCadastrados.get(veiculo.getRepositoryID());
            veiculosCadastrados.remove(substituir);
            veiculosCadastrados.add(veiculo.getRepositoryID(), veiculo);
            substituir.setRepositoryID(null);
        }
    }


    @Override
    public void deletar(String placaDoVeiculo) {
        Optional<Veiculo> veiculoProcurado = procuparPorIdentificador(placaDoVeiculo);
        if (veiculoProcurado.isEmpty()) {
            System.out.println("Veiculo nao encontrado!");
        } else {
            veiculosCadastrados.remove(veiculoProcurado.get());
            veiculoProcurado.get().setRepositoryID(null);
            System.out.println(veiculoProcurado.get().getNome() + "removido(a).");
        }
    }

    @Override
    public List<Veiculo> listarCadastrados() {
        return veiculosCadastrados;
    }

    @Override
    public Optional<Veiculo> procuparPorIdentificador(String placaDoVeiculo) {
        return veiculosCadastrados.stream().filter(
                veiculo -> veiculo.getPlaca().equals(placaDoVeiculo)).findFirst();

    }

    @Override
    public List<Veiculo> procurarPorNome(String nomeDoVeiculo) {
        return veiculosCadastrados.stream().filter(veiculo -> veiculo.getNome().toLowerCase().contains(nomeDoVeiculo)).toList();
    }

    @Override
    public int quantidadeDeCadastros() {
        return veiculosCadastrados.size();
    }

    @Override
    public void deletarTodosOsCadastros() {

        for (Veiculo v : veiculosCadastrados) {
            v.setRepositoryID(null);
        }
        veiculosCadastrados.clear();
    }
}
