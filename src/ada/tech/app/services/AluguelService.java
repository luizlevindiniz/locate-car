package ada.tech.app.services;

import ada.tech.app.models.*;
import ada.tech.app.repositories.api.AluguelRepository;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.repositories.impl.AluguelRepositoryImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class AluguelService {
    private static final AluguelRepository<Aluguel> aluguelRepository = new AluguelRepositoryImpl();

    public static void alugarVeiculo(Pessoa pessoa, Veiculo veiculo, LocalDateTime dataInicio, LocaisAluguel localAluguel,
                                     VeiculoRepository veiculos, PessoaRepository pessoas) {

        if (!veiculo.isAlugado()) {

            // checar se veiculo ja esta cadastrado
            if (verificarCadastroPessoa(pessoa, pessoas)) {
                throw new RuntimeException("Pessoa nao cadastrado no repositorio!\n");
            }

            // checar se pessoa ja esta cadastrada
            if (verificarCadastroVeiculo(veiculo, veiculos)) {
                throw new RuntimeException("Veiculo nao cadastrado no repositorio!\n");
            }
            // checar se o veiculo esta realmente disponivel para locacao
            if (verificaDisponibilidadeVeiculo(veiculo, veiculos)) {
                throw new RuntimeException("Veiculo ja possui aluguel ativo!\n");
            }

            // registrar aluguel
            Aluguel aluguel = Aluguel.builder().pessoa(pessoa).veiculo(veiculo).dataInicio(dataInicio).locaisAluguel(localAluguel).build();
            aluguelRepository.registrarAlugel(aluguel);
            pessoa.registrarVeiculoAlugado(veiculo);
            veiculo.setAlugado(true);
            System.out.printf("Veiculo %s de placa %s alugado para %s\n", veiculo.getNome(), veiculo.getPlaca()
                    , pessoa.getNome());

        } else {
            throw new RuntimeException("Nao e possivel alugar um veiculo que ja possui aluguel ativo!\n");

        }
    }

    public static void devolverVeiculo(Veiculo veiculo, LocalDateTime dataFim, LocaisAluguel localDevolucao, VeiculoRepository veiculos) {
        if (veiculo.isAlugado()) {

            // checar cadastro do veiculo
            if (verificarCadastroVeiculo(veiculo, veiculos)) {
                throw new RuntimeException("Veiculo nao cadastrado no repositorio!\n");
            }

            // checar se o veiculo esta realmente indisponivel para locacao
            if (!verificaDisponibilidadeVeiculo(veiculo, veiculos)) {
                throw new RuntimeException("Veiculo nao possui aluguel ativo!\n");
            }


            Aluguel devolvido = aluguelRepository.devolverVeiculo(veiculo.getPlaca(), localDevolucao);
            devolvido.getPessoa().removerVeiculoAlugado(veiculo);
            devolvido.getVeiculo().setAlugado(false);
            System.out.printf("Veiculo %s de placa %s devolvido na %s!\n", veiculo.getNome(), veiculo.getPlaca(), localDevolucao);
            calcularValorTotal(devolvido, dataFim);
        } else {
            throw new RuntimeException("Nao e possivel devolver um veiculo que nao esteja alugado!\n");

        }

    }

    public static void printarVeiculosAlugados() {
        aluguelRepository.listarAlugueis().forEach(System.out::println);

    }

    private static boolean verificarCadastroPessoa(Pessoa pessoa, PessoaRepository pessoas) {
        return pessoas.procuparPorIdentificador(pessoa.getIdentificador()).isEmpty();

    }

    private static boolean verificarCadastroVeiculo(Veiculo veiculo, VeiculoRepository veiculos) {
        return veiculos.procuparPorIdentificador(veiculo.getPlaca()).isEmpty();
    }

    private static boolean verificaDisponibilidadeVeiculo(Veiculo veiculo, VeiculoRepository veiculos) {
        Optional<Veiculo> veiculoProcurado = veiculos.procuparPorIdentificador(veiculo.getPlaca());
        return veiculoProcurado.isPresent() && veiculoProcurado.get().isAlugado();
    }


    private static void calcularValorTotal(Aluguel devolucao, LocalDateTime dataFim) {
        TipoVeiculo tipoVeiculo = devolucao.getVeiculo().getTipo();

        double valorDiaria = switch (tipoVeiculo) {
            case SUV -> 200.0;
            case MEDIO -> 150.0;
            case PEQUENO -> 100.0;
        };

        Duration duracaoDaLocacao = Duration.between(devolucao.getDataInicio(), dataFim);
        long minutosSobrando = duracaoDaLocacao.toMinutes() % 60;
        long segundosSobrando = duracaoDaLocacao.getSeconds() % 60;

        double tempoAlugadoEmHoras = (double) ChronoUnit.HOURS.between(devolucao.getDataInicio(), dataFim);
        int tempoAlugadoEmDias = (int) (tempoAlugadoEmHoras / 24);

        if ((tempoAlugadoEmHoras % 24) > 0) {
            tempoAlugadoEmDias += 1;
        } else {
            if (minutosSobrando > 0 || segundosSobrando > 0) {
                tempoAlugadoEmDias += 1;
            }
        }

        double valorTotal = valorDiaria * tempoAlugadoEmDias;

        if (devolucao.getPessoa() instanceof PF && tempoAlugadoEmDias > 5) {
            valorTotal *= 0.95;
        } else if (devolucao.getPessoa() instanceof PJ && tempoAlugadoEmDias > 3) {
            valorTotal *= 0.90;
        }

        System.out.printf("Total a cobrar: R$ %.2f - %d dias alugando o %s.\n", valorTotal,
                tempoAlugadoEmDias, devolucao.getVeiculo().getNome());

    }

};


