package ada.tech.app.repositories.impl;

import ada.tech.app.models.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepositoryImpl implements ada.tech.app.repositories.VeiculoRepository {

    private final List<Veiculo> veiculos = new ArrayList<>();


    @Override
    public Veiculo criar(Veiculo o) {
        if (o.getRepositoryID() == null) {
            o.setRepositoryID(veiculos.size());
            veiculos.add(o);
        } else {
            Veiculo substituir = veiculos.get(o.getRepositoryID());
            veiculos.remove(substituir);
            veiculos.add(o.getRepositoryID(), o);
        }
        return o;
    }


    @Override
    public void deletar(String placa) {
        Optional<Veiculo> v = procurarPorId(placa);
        if (v.isEmpty()) {
            System.out.println("Veiculo nao encontrado!");
        } else {
            veiculos.remove(v.get());
            System.out.println("Veiculo Removido!");
        }
    }

    @Override
    public List<Veiculo> listarTodos() {
        return veiculos;
    }

    @Override
    public Optional<Veiculo> procurarPorId(String placa) {
        return veiculos.stream().filter(
                veiculo -> veiculo.getPlaca().equals(placa)).findFirst();

    }

    @Override
    public List<Veiculo> procurarPorNome(String nome) {
        return veiculos.stream().filter(veiculo -> veiculo.getNome().toLowerCase().contains(nome)).toList();
    }
}
