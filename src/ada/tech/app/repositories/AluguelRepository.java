package ada.tech.app.repositories;

import ada.tech.app.models.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository<T extends Aluguel> {
    void registrarAlugel(T o);

    void devolverPorPlaca(String placa);


    List<T> listarTodos();

    Optional<T> procuparPorPlaca(String placa);


}
