package ada.tech.app.repositories.api;

import ada.tech.app.models.Aluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository<T extends Aluguel> {
    void registrarAlugel(T o);

    T devolverVeiculo(String placa);


    List<T> listarTodos();

    Optional<T> procuparPorPlaca(String placa);


}
