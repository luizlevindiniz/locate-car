package ada.tech.app.repositories.impl;

import ada.tech.app.models.Aluguel;
import ada.tech.app.repositories.api.AluguelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelRepositoryImpl implements AluguelRepository<Aluguel> {
    private final List<Aluguel> listaAlugueis = new ArrayList<>();

    @Override
    public void registrarAlugel(Aluguel o) {
        if (o.getRepositoryID() == null) {
            o.setRepositoryID(listaAlugueis.size());

            listaAlugueis.add(o);
        }
    }

    @Override
    public Aluguel devolverVeiculo(String placa) {

        Optional<Aluguel> aluguel = procuparPorPlaca(placa);
        if (aluguel.isEmpty()) {
            throw new RuntimeException("Este veiculo nao esta alugado!");
        } else {
            listaAlugueis.remove(aluguel.get());
            return aluguel.get();
        }

    }


    @Override
    public List<Aluguel> listarTodos() {
        return listaAlugueis;
    }

    @Override
    public Optional<Aluguel> procuparPorPlaca(String placa) {
        return listaAlugueis.stream().filter(aluguel -> aluguel.getVeiculo().getPlaca().equals(placa)).findFirst();
    }


}
