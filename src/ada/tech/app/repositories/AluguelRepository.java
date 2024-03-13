package ada.tech.app.repositories;

import ada.tech.app.models.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository<T extends Aluguel> {
    void alugar(T o);

    void devolverPorPlaca(String placa);

    void devolverPorCPF(String cpf);

    void devolverPorCNPJ(String cnpj);

    List<T> listarTodos();

    Optional<T> procuparPorPlaca(String placa);

    Optional<T> procuparPorCPF(String placa);

    Optional<T> procuparPorCNPJ(String placa);

    int tamanhoDaLista();
}
