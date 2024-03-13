package ada.tech.app;

import ada.tech.app.models.*;
import ada.tech.app.repositories.VeiculoRepository;
import ada.tech.app.repositories.impl.VeiculoRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VeiculoRepository veiculos = new VeiculoRepositoryImpl();
        Veiculo v1 = Veiculo.builder().nome("Cronos").placa("XYZT3CH").marca("Fiat")
                .estaAlugado(false).tipo(TipoCarro.MEDIO).build();

        // create veiculo
        veiculos.criar(v1);
        List<Veiculo> listarTodos = veiculos.listarTodos();
        listarTodos.forEach(System.out::println);

        // update veiculo
        v1.setTipo(TipoCarro.PEQUENO);
        v1.setNome("Uno Mille");
        v1.setPlaca("4D4T3CH");
        veiculos.criar(v1);
        listarTodos.forEach(System.out::println);


        try{
            veiculos.criar(v1);
        } catch (Exception e){
            System.out.println(e);
        }

        // procurar por parte do nome
        List<Veiculo> procurarPorNome = veiculos.procurarPorNome("mille");
        procurarPorNome.forEach(System.out::println);

        // cadastrar PF
        PF pf1 = new PF("Bruno Pinho","084.518.526-88",false);
        PJ pj1 = new PJ("Ada Tech","40.846.322/0001-70",true);

        System.out.println(pf1);
        System.out.println(pj1);

    }
}
