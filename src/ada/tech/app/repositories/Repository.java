package ada.tech.app.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    T criar(T o);

    void deletar(String id);

    List<T> listarTodos();

   Optional<T> procurarPorPlaca(String placa);
}