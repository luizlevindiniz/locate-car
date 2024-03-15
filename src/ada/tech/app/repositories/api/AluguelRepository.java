package ada.tech.app.repositories.api;

import ada.tech.app.models.Aluguel;
import ada.tech.app.models.LocaisAluguel;

import java.util.List;
import java.util.Optional;

public interface AluguelRepository<T extends Aluguel> {
    void registrarAlugel(T o);

    T devolverVeiculo(String placa, LocaisAluguel locaisAluguel);


    List<T> listarAlugueis();

    Optional<T> procuparPorPlaca(String placa);


}
