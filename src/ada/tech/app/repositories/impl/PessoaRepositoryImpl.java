package ada.tech.app.repositories.impl;

import ada.tech.app.models.Pessoa;
import ada.tech.app.repositories.api.PessoaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepositoryImpl implements PessoaRepository {
    private final List<Pessoa> listaPessoas = new ArrayList<>();

    @Override
    public void adicionar(Pessoa o) {
        boolean jaExiste = procuparPorIdentificador(o.getIdentificador()).isEmpty();
        if (!jaExiste) {
            throw new RuntimeException("Pessoa ja cadastrada!");
        }

        if (o.getRepositoryID() == null) {
            o.setRepositoryID(listaPessoas.size());

            listaPessoas.add(o);
        } else {
            Pessoa substituir = listaPessoas.get(o.getRepositoryID());
            listaPessoas.remove(substituir);
            listaPessoas.add(o.getRepositoryID(), o);
        }
    }

    @Override
    public void deletar(String identificador) {
        Optional<Pessoa> pessoa = procuparPorIdentificador(identificador);
        if (pessoa.isEmpty()) {
            System.out.println("Pessoa nao encontrada!");
        } else {
            listaPessoas.remove(pessoa.get());
            System.out.println(pessoa.get().getNome() + "removido(a).");
        }

    }

    @Override
    public List<Pessoa> listarTodos() {
        return listaPessoas;
    }

    @Override
    public Optional<Pessoa> procuparPorIdentificador(String identificador) {
        return listaPessoas.stream().filter(pessoa -> pessoa.getIdentificador().equals(identificador)).findFirst();
    }

    @Override
    public int tamanhoDaLista() {
        return listaPessoas.size();
    }

    @Override
    public void deletarTodos() {
        listaPessoas.clear();
    }
}
