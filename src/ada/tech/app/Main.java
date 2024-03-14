package ada.tech.app;

import ada.tech.app.repositories.api.PessoaRepository;
import ada.tech.app.repositories.api.VeiculoRepository;
import ada.tech.app.repositories.impl.PessoaRepositoryImpl;
import ada.tech.app.repositories.impl.VeiculoRepositoryImpl;
import ada.tech.app.tests.TesteAlugueis;
import ada.tech.app.tests.TestePessoas;
import ada.tech.app.tests.TesteVeiculos;

public class Main {
    public static void main(String[] args) {
        // --- Repos --- //
        VeiculoRepository veiculos = new VeiculoRepositoryImpl();
        PessoaRepository pessoas = new PessoaRepositoryImpl();

        System.out.println("// --- Testes de Veiculos --- //");
        TesteVeiculos.testarVeiculos(veiculos);

        System.out.println("// --- Testes de PF e PJ --- //");
        TestePessoas.testarPessoas(pessoas);

        System.out.println("// --- Testes de Aluguel --- //");
        TesteAlugueis.testarAlugueis(pessoas, veiculos);
    }

}
