package ada.tech.app.repositories.impl;

import ada.tech.app.models.Aluguel;
import ada.tech.app.repositories.api.AluguelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelRepositoryImpl implements AluguelRepository<Aluguel> {
    private final List<Aluguel> alugueisVigentes = new ArrayList<>();

    @Override
    public void registrarAlugel(Aluguel aluguel) {
        if (aluguel.getRepositoryID() == null) {
            aluguel.setRepositoryID(alugueisVigentes.size());

            alugueisVigentes.add(aluguel);
        }
    }

    @Override
    public Aluguel devolverVeiculo(String placa) {

        Optional<Aluguel> aluguelProcurado = procuparPorPlaca(placa);
        if (aluguelProcurado.isEmpty()) {
            throw new RuntimeException("Este veiculo nao esta alugado!");
        } else {
            alugueisVigentes.remove(aluguelProcurado.get());
            aluguelProcurado.get().setRepositoryID(null);
            return aluguelProcurado.get();
        }

    }


    @Override
    public List<Aluguel> listarAlugueis() {
        return alugueisVigentes;
    }

    @Override
    public Optional<Aluguel> procuparPorPlaca(String placa) {
        return alugueisVigentes.stream().filter(aluguel -> aluguel.getVeiculo().getPlaca().equals(placa)).findFirst();
    }


}
