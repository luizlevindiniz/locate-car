package ada.tech.app;

import ada.tech.app.models.*;
import ada.tech.app.repositories.PFRepository;
import ada.tech.app.repositories.PJRepository;
import ada.tech.app.repositories.VeiculoRepository;
import ada.tech.app.repositories.impl.PFRepositoryImpl;
import ada.tech.app.repositories.impl.PJRepositoryImpl;
import ada.tech.app.repositories.impl.VeiculoRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- Repos --- //
        VeiculoRepository veiculos = new VeiculoRepositoryImpl();
        PFRepository pfs = new PFRepositoryImpl();
        PJRepository pjs = new PJRepositoryImpl();

        // --- Testes de Veiculos --- //
        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("XYZT3CH").marca("Fiat")
                .estaAlugado(false).tipo(TipoCarro.MEDIO).build();

        // create veiculo
        veiculos.criar(v1);
        List<Veiculo> listarVeiculos = veiculos.listarTodos();
        listarVeiculos.forEach(System.out::println);

        // update veiculo
        Veiculo v2 = Veiculo.builder().nome("Uno Mille").placa("4D4T3CH").marca("Fiat")
                .estaAlugado(false).tipo(TipoCarro.PEQUENO).repositoryID(veiculos.tamanhoDaLista() - 1).build();
        veiculos.criar(v2);
        listarVeiculos.forEach(System.out::println);

        // tentar criar veiculo com a mesma placa
        Veiculo v3 = Veiculo.builder().nome("Cronos").placa("4D4T3CH").marca("Fiat")
                .estaAlugado(false).tipo(TipoCarro.MEDIO).build();
        try {
            veiculos.criar(v3);
        } catch (Exception e) {
            System.out.println(e);
        }

        // procurar por parte do nome
        List<Veiculo> procurarPorNome = veiculos.procurarPorNome("mille");
        procurarPorNome.forEach(System.out::println);


        // --- Testes de PF e PJ --- //
        PF pf1 = new PF("Bruno Pinho", "084.518.526-88", false);
        PJ pj1 = new PJ("Ada Tech", "40.846.322/0001-70", false);

        // create PF e PJ
        pfs.criar(pf1);
        pjs.criar(pj1);
        List<PF> listarPFs = pfs.listarTodos();
        List<PJ> listarPJs = pjs.listarTodos();
        listarPFs.forEach(System.out::println);
        listarPJs.forEach(System.out::println);

        // update PF e PJ
        PF pf2 = new PF("Sou uma PF", "111.222.333-44", false);
        pf2.setRepositoryID(pfs.tamanhoDaLista() - 1);
        PJ pj2 = new PJ("Concorrente Ada Tech", "41.846.322/0001-70", false);
        pj2.setRepositoryID(pjs.tamanhoDaLista() - 1);
        pfs.criar(pf2);
        pjs.criar(pj2);
        listarPFs.forEach(System.out::println);
        listarPJs.forEach(System.out::println);

        // tentar criar PF e PJ com os mesmos identificadores
        PF pf3 = new PF("Bruno Pinho", "111.222.333-44", false);
        PJ pj3 = new PJ("Ada Tech", "41.846.322/0001-70", false);

        try {
            pfs.criar(pf3);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            pjs.criar(pj3);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
