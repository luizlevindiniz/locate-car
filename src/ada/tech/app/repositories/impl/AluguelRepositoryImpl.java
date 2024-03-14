package ada.tech.app.repositories.impl;

import ada.tech.app.models.Aluguel;
import ada.tech.app.repositories.AluguelRepository;

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
    public void devolverPorPlaca(String identificador) {

        Optional<Aluguel> aluguel = procuparPorPlaca(identificador);
        if (aluguel.isEmpty()) {
            System.out.println("Aluguel nao encontrado para este veiculo!");
        } else {
            aluguel.get().getVeiculo().setEstaAlugado(false);
            aluguel.get().getPessoa().setAlugouCarro(false);
            listaAlugueis.remove(aluguel.get());
            System.out.println(aluguel.get().getPessoa().getNome() + " devolveu o veiculo!");
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
