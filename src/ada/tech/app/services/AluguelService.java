package ada.tech.app.services;

import ada.tech.app.models.*;
import ada.tech.app.repositories.api.AluguelRepository;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.repositories.impl.AluguelRepositoryImpl;

import java.sql.Time;
import java.time.LocalDateTime;
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
        boolean isSUV = devolucao.getVeiculo().getTipo() == TipoVeiculo.SUV;

        double valorDiaria = isSUV ? 200.0 : (devolucao.getVeiculo().getTipo() == TipoVeiculo.MEDIO ? 150.0 : 100.0);

        int duracaoHoras = (int) ChronoUnit.HOURS.between(devolucao.getDataInicio(), dataFim);
        int duracaoEmDias = (duracaoHoras / 24);
        if ((duracaoHoras % 24) > 0) {
            duracaoEmDias += 1;
        }

        double valorTotal = valorDiaria * duracaoEmDias;

        if (devolucao.getPessoa() instanceof PF && duracaoEmDias > 5) {
            valorTotal *= 0.95;
        } else if (devolucao.getPessoa() instanceof PJ && duracaoEmDias > 3) {
            valorTotal *= 0.90;
        }

        System.out.printf("Total a cobrar: R$ %.2f - %d dias alugando o %s.\n", valorTotal,
                duracaoEmDias, devolucao.getVeiculo().getNome());

    }

};


