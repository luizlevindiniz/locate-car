package ada.tech.app.services;

import ada.tech.app.models.Aluguel;
import ada.tech.app.models.Pessoa;
import ada.tech.app.models.Veiculo;
import ada.tech.app.repositories.impl.AluguelRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class AluguelService {
    private static AluguelRepositoryImpl aluguelRepository = new AluguelRepositoryImpl();

    public static Aluguel alugarVeiculo(Pessoa pessoa, Veiculo veiculo, LocalDate dataInicio, int duracaoEmDias) {
        if (!pessoa.isAlugouCarro() && !veiculo.isEstaAlugado()) {

            Aluguel aluguel = Aluguel.builder().pessoa(pessoa).veiculo(veiculo).dataInicio(dataInicio).duracaoEmDias(duracaoEmDias).build();
            aluguelRepository.registrarAlugel(aluguel);
        }
        throw new RuntimeException("Veiculo ou pessoa ja possui um aluguel!");
    }

    public static void devolverVeiculo(Veiculo veiculo) {
        if (veiculo.isEstaAlugado()) {
            aluguelRepository.devolverPorPlaca(veiculo.getPlaca());
        }

    }

    public static List<Aluguel> listarAlugueis() {
        return aluguelRepository.listarTodos();
    }
};


