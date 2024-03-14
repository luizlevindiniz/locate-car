package ada.tech.app.repositories.impl;

import ada.tech.app.models.Pessoa;
import ada.tech.app.repositories.api.PessoaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepositoryImpl implements PessoaRepository {
    private final List<Pessoa> pessoasCadastradas = new ArrayList<>();

    @Override
    public void adicionar(Pessoa pessoa) {
        boolean pessoaJaExiste = procuparPorIdentificador(pessoa.getIdentificador()).isEmpty();
        if (!pessoaJaExiste) {
            throw new RuntimeException("Pessoa ja cadastrada!");
        }

        if (pessoa.getRepositoryID() == null) {
            pessoa.setRepositoryID(pessoasCadastradas.size());

            pessoasCadastradas.add(pessoa);
        } else {
            Pessoa substituir = pessoasCadastradas.get(pessoa.getRepositoryID());
            pessoasCadastradas.remove(substituir);
            pessoasCadastradas.add(pessoa.getRepositoryID(), pessoa);
            substituir.setRepositoryID(null);
        }
    }

    @Override
    public void deletar(String identificador) {
        Optional<Pessoa> pessoaProcurada = procuparPorIdentificador(identificador);
        if (pessoaProcurada.isEmpty()) {
            System.out.println("Pessoa nao encontrada!");
        } else {
            pessoasCadastradas.remove(pessoaProcurada.get());
            pessoaProcurada.get().setRepositoryID(null);
            System.out.println(pessoaProcurada.get().getNome() + "removido(a).");
        }

    }

    @Override
    public List<Pessoa> listarCadastrados() {
        return pessoasCadastradas;
    }

    @Override
    public Optional<Pessoa> procuparPorIdentificador(String identificador) {
        return pessoasCadastradas.stream().filter(pessoa -> pessoa.getIdentificador().equals(identificador)).findFirst();
    }

    @Override
    public int quantidadeDeCadastros() {
        return pessoasCadastradas.size();
    }

    @Override
    public void deletarTodosOsCadastros() {

        for (Pessoa p : pessoasCadastradas) {
            p.setRepositoryID(null);
        }
        pessoasCadastradas.clear();
    }
}
