package ada.tech.app.repositories.api;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void adicionar(T o);

    void deletar(String identificador);

    List<T> listarTodos();

    Optional<T> procuparPorIdentificador(String identificador);

    int tamanhoDaLista();

    void deletarTodos();

}