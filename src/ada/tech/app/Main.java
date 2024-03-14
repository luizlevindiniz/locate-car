package ada.tech.app;

import ada.tech.app.models.PF;
import ada.tech.app.models.PJ;
import ada.tech.app.models.TipoVeiculo;
import ada.tech.app.models.Veiculo;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.repositories.impl.PessoaRepositoryImpl;
import ada.tech.app.repositories.impl.VeiculoRepositoryImpl;
import ada.tech.app.services.AluguelService;
import ada.tech.app.tests.TestePessoas;
import ada.tech.app.tests.TesteVeiculos;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // --- Repos --- //
        VeiculoRepository veiculos = new VeiculoRepositoryImpl();
        PessoaRepository pessoas = new PessoaRepositoryImpl();

        // --- Testes de Veiculos --- //
        TesteVeiculos.testarVeiculos(veiculos);

        // --- Testes de PF e PJ --- //
        TestePessoas.testarPessoas(pessoas);

        // --- Testes de Aluguel --- //
        PF pf1 = new PF("Bruno Pinho", "084.518.526-88", false);
        PJ pj1 = new PJ("Ada Tech", "40.846.322/0001-70", false);

        pessoas.adicionar(pf1);
        pessoas.adicionar(pj1);

        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("4D4TECH").marca("Fiat")
                .alugado(false).tipo(TipoVeiculo.MEDIO).build();
        Veiculo v2 = Veiculo.builder().nome("Tiggo 6").placa("ADAT3CH").marca("Chery")
                .alugado(false).tipo(TipoVeiculo.SUV).build();

        veiculos.adicionar(v1);
        veiculos.adicionar(v2);

        AluguelService.alugarVeiculo(pf1, v1, LocalDate.now(), veiculos, pessoas);
        AluguelService.alugarVeiculo(pj1, v2, LocalDate.now(), veiculos, pessoas);

        AluguelService.printarVeiculosAlugados();

        AluguelService.devolverVeiculo(v1, LocalDate.now().plusDays(1));
        AluguelService.devolverVeiculo(v2, LocalDate.now().plusDays(7));


    }

}
