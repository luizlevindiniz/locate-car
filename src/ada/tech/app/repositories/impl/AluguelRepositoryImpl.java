package ada.tech.app.repositories.impl;

import ada.tech.app.models.Aluguel;
import ada.tech.app.repositories.AluguelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelRepositoryImpl implements AluguelRepository {
    private final List<Aluguel> listaAlugueis = new ArrayList<>();

    @Override
    public void alugar(Aluguel o) {
        if (o.getRepositoryID() == null) {
            o.setRepositoryID(listaAlugueis.size());

            listaAlugueis.add(o);
        } else {
            Aluguel substituir = listaAlugueis.get(o.getRepositoryID());
            listaAlugueis.remove(substituir);
            listaAlugueis.add(o.getRepositoryID(), o);
        }
    }

    @Override
    public void devolverPorPlaca(String identificador) {

    }

    @Override
    public void devolverPorCPF(String cpf) {

    }

    @Override
    public void devolverPorCNPJ(String cnpj) {

    }

    @Override
    public List<Aluguel> listarTodos() {
        return listaAlugueis;
    }

    @Override
    public Optional procuparPorPlaca(String placa) {
        return Optional.empty();
    }

    @Override
    public Optional procuparPorCPF(String placa) {
        return Optional.empty();
    }

    @Override
    public Optional procuparPorCNPJ(String placa) {
        return Optional.empty();
    }

    @Override
    public int tamanhoDaLista() {
        return listaAlugueis.size();
    }
}
