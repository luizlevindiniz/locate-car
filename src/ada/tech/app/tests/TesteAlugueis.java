package ada.tech.app.tests;

import ada.tech.app.models.*;
import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.services.AluguelService;

import java.time.LocalDateTime;

public class TesteAlugueis {

    public static void testarAlugueis(PessoaRepository pessoas, VeiculoRepository veiculos) {
        PF pf1 = new PF("Bruno Pinho", "084.518.526-88");
        PJ pj1 = new PJ("Ada Tech", "40.846.322/0001-70");
        PJ pj2 = new PJ("Ada Tech 2", "40.846.322/0001-99");

        pessoas.adicionar(pf1);
        pessoas.adicionar(pj1);
        pessoas.adicionar(pj2);

        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("4D4TECH").marca("Fiat").tipo(TipoVeiculo.MEDIO).build();
        Veiculo v2 = Veiculo.builder().nome("Tiggo 6").placa("ADAT3CH").marca("Chery").tipo(TipoVeiculo.SUV).build();
        Veiculo v3 = Veiculo.builder().nome("Tiggo 7").placa("4D4T3CH").marca("Cherry").tipo(TipoVeiculo.SUV).build();

        veiculos.adicionar(v1);
        veiculos.adicionar(v2);
        veiculos.adicionar(v3);

        AluguelService.alugarVeiculo(pf1, v1, LocalDateTime.now(), LocaisAluguel.SEDE, veiculos, pessoas);
        AluguelService.alugarVeiculo(pj1, v2, LocalDateTime.now(), LocaisAluguel.FILIAL_4, veiculos, pessoas);
        AluguelService.alugarVeiculo(pj2, v3, LocalDateTime.now(), LocaisAluguel.FILIAL_2, veiculos, pessoas);

        System.out.println();

        AluguelService.printarVeiculosAlugados();

        System.out.println();

        AluguelService.devolverVeiculo(v1, LocalDateTime.now().plusHours(22), LocaisAluguel.SEDE, veiculos);
        AluguelService.devolverVeiculo(v2, LocalDateTime.now().plusHours(30), LocaisAluguel.FILIAL_1,  veiculos);
        AluguelService.devolverVeiculo(v3, LocalDateTime.now().plusDays(1), LocaisAluguel.FILIAL_2, veiculos);

        AluguelService.printarVeiculosAlugados(); // empty


    }
}
