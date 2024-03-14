package ada.tech.app.tests;

import ada.tech.app.models.PF;
import ada.tech.app.models.PJ;
import ada.tech.app.models.TipoVeiculo;
import ada.tech.app.models.Veiculo;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.services.AluguelService;

import java.time.LocalDate;

public class TesteAlugueis {

    public static void testarAlugueis(PessoaRepository pessoas, VeiculoRepository veiculos) {
        PF pf1 = new PF("Bruno Pinho", "084.518.526-88");
        PJ pj1 = new PJ("Ada Tech", "40.846.322/0001-70");

        pessoas.adicionar(pf1);
        pessoas.adicionar(pj1);

        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("4D4TECH").marca("Fiat").tipo(TipoVeiculo.MEDIO).build();
        Veiculo v2 = Veiculo.builder().nome("Tiggo 6").placa("ADAT3CH").marca("Chery").tipo(TipoVeiculo.SUV).build();

        veiculos.adicionar(v1);
        veiculos.adicionar(v2);

        AluguelService.alugarVeiculo(pf1, v1, LocalDate.now(), veiculos, pessoas);
        AluguelService.alugarVeiculo(pj1, v2, LocalDate.now(), veiculos, pessoas);

        System.out.println();

        AluguelService.printarVeiculosAlugados();

        System.out.println();

        AluguelService.devolverVeiculo(v1, LocalDate.now().plusDays(2), veiculos);
        AluguelService.devolverVeiculo(v2, LocalDate.now().plusDays(7), veiculos);

        AluguelService.printarVeiculosAlugados(); // empty


    }
}
