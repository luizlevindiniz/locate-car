package ada.tech.app.tests;

import ada.tech.app.models.*;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.services.AluguelService;

import java.time.LocalDateTime;

public class TesteAlugueis {

    public static void testarAlugueis(PessoaRepository pessoas, VeiculoRepository veiculos) {
        PF pf1 = new PF("Bruno Pinho", "084.518.526-88");
        PF pf2 = new PF("Max Verstappen", "830.460.530-96");
        PJ pj1 = new PJ("Ada Tech", "40.846.322/0001-70");
        PJ pj2 = new PJ("Ada Tech 2", "40.846.322/0001-99");

        pessoas.adicionar(pf1);
        pessoas.adicionar(pj1);
        pessoas.adicionar(pj2);

        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("4D4TECH").marca("Fiat").tipo(TipoVeiculo.MEDIO).build();
        Veiculo v2 = Veiculo.builder().nome("Dolphin").placa("EL3TR1C").marca("BID").tipo(TipoVeiculo.PEQUENO).build();
        Veiculo v3 = Veiculo.builder().nome("Tiggo 7").placa("4D4T3CH").marca("Chery").tipo(TipoVeiculo.SUV).build();

        veiculos.adicionar(v1);
        veiculos.adicionar(v2);
        veiculos.adicionar(v3);

        AluguelService.alugarVeiculo(pf1, v1, LocalDateTime.parse("2024-03-10T15:30:00"), LocaisAluguel.SEDE, veiculos, pessoas);
        AluguelService.alugarVeiculo(pj1, v2, LocalDateTime.parse("2024-03-10T15:30:00"), LocaisAluguel.FILIAL_4, veiculos, pessoas);
        AluguelService.alugarVeiculo(pj2, v3, LocalDateTime.parse("2024-03-10T15:30:00"), LocaisAluguel.FILIAL_2, veiculos, pessoas);

        System.out.println();

        AluguelService.printarVeiculosAlugados();

        // tentar alugar um veiculo ja alugado
        try {
            AluguelService.alugarVeiculo(pf2, v1, LocalDateTime.parse("2024-03-10T15:30:00"), LocaisAluguel.SEDE, veiculos, pessoas);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println();

        // 2 dias
        AluguelService.devolverVeiculo(v1, LocalDateTime.parse("2024-03-11T15:30:01"), LocaisAluguel.SEDE, veiculos);
        // 5 dias
        AluguelService.devolverVeiculo(v2, LocalDateTime.parse("2024-03-15T15:30:00"), LocaisAluguel.FILIAL_1, veiculos);
        // 11 dias
        AluguelService.devolverVeiculo(v3, LocalDateTime.parse("2024-03-20T19:48:22"), LocaisAluguel.FILIAL_2, veiculos);

        AluguelService.printarVeiculosAlugados(); // empty


    }
}
