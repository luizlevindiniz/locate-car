package ada.tech.app.services;

import ada.tech.app.models.Aluguel;
import ada.tech.app.models.Pessoa;
import ada.tech.app.models.Veiculo;
import ada.tech.app.repositories.api.AluguelRepository;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.repositories.impl.AluguelRepositoryImpl;

import java.time.LocalDate;
import java.util.Optional;

public class AluguelService {
    private static final AluguelRepository<Aluguel> aluguelRepository = new AluguelRepositoryImpl();

    public static void alugarVeiculo(Pessoa pessoa, Veiculo veiculo, LocalDate dataInicio, VeiculoRepository veiculos,
                                     PessoaRepository pessoas) {

        if (!pessoa.isAlugouCarro() && !veiculo.isAlugado()) {

            // checar cadastro do veiculo e da pessoa
            if (!verificarCadastro(pessoa, veiculo, veiculos, pessoas)) {
                throw new RuntimeException("Veiculo ou pessoa nao cadastrado no repositorio!\n");
            }

            // checar se o veiculo esta mesmo disponivel
            if (verificaDisponibilidadeVeiculo(veiculos, veiculo)) {
                throw new RuntimeException("Veiculo ja possui aluguel ativo!\n");
            }

            // registrar aluguel

            Aluguel aluguel = Aluguel.builder().pessoa(pessoa).veiculo(veiculo).dataInicio(dataInicio).build();
            aluguelRepository.registrarAlugel(aluguel);
            pessoa.setAlugouCarro(true);
            veiculo.setAlugado(true);
            System.out.printf("Veiculo %s alugado para %s\n", veiculo.getNome(), pessoa.getNome());

        } else {
            throw new RuntimeException("Parece que este veiculo ou pessoa ja possui aluguel ativo!\n");

        }
    }

    public static void devolverVeiculo(Veiculo veiculo, LocalDate dataFim) {
        if (veiculo.isAlugado()) {
            Aluguel devolvido = aluguelRepository.devolverVeiculo(veiculo.getPlaca());
            devolvido.getPessoa().setAlugouCarro(false);
            devolvido.getVeiculo().setAlugado(false);
            System.out.printf("Veiculo %s de placa %s devolvido!\n", veiculo.getNome(), veiculo.getPlaca());
        }

    }

    public static void printarVeiculosAlugados() {
        aluguelRepository.listarTodos().forEach(System.out::println);

    }

    private static boolean verificarCadastro(Pessoa pessoa, Veiculo veiculo, VeiculoRepository veiculos, PessoaRepository pessoas) {
        // checar se pessoa ja esta cadastrada
        if (pessoas.procuparPorIdentificador(pessoa.getIdentificador()).isEmpty()) {
            return false;
        }

        // checar se veiculo ja esta cadastrado
        if (veiculos.procuparPorIdentificador(veiculo.getPlaca()).isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean verificaDisponibilidadeVeiculo(VeiculoRepository veiculos, Veiculo veiculo) {
        Optional<Veiculo> veiculoOptional = veiculos.procuparPorIdentificador(veiculo.getPlaca());
        return veiculoOptional.isPresent() && veiculoOptional.get().isAlugado();
    }

    /*
    public double calcularValorTotal() {
        boolean isSUV = veiculo.getTipo() == TipoCarro.SUV;

        double valorDiaria = isSUV ? 200.0 : (veiculo.getTipo() == TipoCarro.MEDIO ? 150.0 : 100.0);

        double valorTotal = valorDiaria * duracaoEmDias;

        if (pessoa instanceof PF && duracaoEmDias > 5) {
            valorTotal *= 0.95;
        } else if (pessoa instanceof PJ && duracaoEmDias > 3) {
            valorTotal *= 0.90;
        }

        return valorTotal;
    }

     */

};


