package ada.tech.app.tests;

import ada.tech.app.models.TipoVeiculo;
import ada.tech.app.models.Veiculo;
import ada.tech.app.repositories.api.VeiculoRepository;

import java.util.List;

public class TesteVeiculos {

    public static void testarVeiculos(VeiculoRepository veiculos) {

        // --- Testes de Veiculos --- //

        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("XYZT3CH").marca("Fiat")
                .alugado(false).tipo(TipoVeiculo.MEDIO).build();

        // create veiculo
        veiculos.adicionar(v1);
        List<Veiculo> listarVeiculos = veiculos.listarTodos();
        listarVeiculos.forEach(System.out::println);

        // update veiculo
        Veiculo v2 = Veiculo.builder().nome("Uno Mille").placa("4D4T3CH").marca("Fiat")
                .alugado(false).tipo(TipoVeiculo.PEQUENO).repositoryID(veiculos.tamanhoDaLista() - 1).build();
        veiculos.adicionar(v2);
        listarVeiculos.forEach(System.out::println);

        // tentar criar veiculo com a mesma placa
        Veiculo v3 = Veiculo.builder().nome("Cronos").placa("4D4T3CH").marca("Fiat")
                .alugado(false).tipo(TipoVeiculo.MEDIO).build();
        try {
            veiculos.adicionar(v3);
        } catch (Exception e) {
            System.out.println(e);
        }

        // procurar por parte do nome
        List<Veiculo> procurarPorNome = veiculos.procurarPorNome("mille");
        procurarPorNome.forEach(System.out::println);
        System.out.println();

        // finalizando
        veiculos.deletarTodos();
    }
}
