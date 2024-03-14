package ada.tech.app.tests;

import ada.tech.app.models.PF;
import ada.tech.app.models.PJ;
import ada.tech.app.models.Pessoa;
import ada.tech.app.repositories.api.PessoaRepository;

import java.util.List;

public class TestePessoas {

    public static void testarPessoas(PessoaRepository pessoas) {

        PF pf1 = new PF("Bruno Pinho", "084.518.526-88");
        PJ pj1 = new PJ("Ada Tech", "40.846.322/0001-70");

        // adicionar PF e PJ no repositorio
        pessoas.adicionar(pf1);
        pessoas.adicionar(pj1);
        List<Pessoa> listaPessoas = pessoas.listarCadastrados();
        listaPessoas.forEach(System.out::println);

        // tentar criar PF e PJ com identificador invalido
        try {
            PF pfx = new PF("Sou uma PF", "111.222.333-44");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            PJ pfx = new PJ("Sou uma PJ", "17A.130.972-52");
        } catch (Exception e) {
            System.out.println(e);
        }

        // atualizar PF e PJ ja existentes no repositorio
        PF pf2 = new PF("Sou uma PF", "177.130.972-52");
        pf2.setRepositoryID(0);
        PJ pj2 = new PJ("Concorrente Ada Tech", "41.846.322/0001-70");
        pj2.setRepositoryID(1);

        pessoas.adicionar(pf2);
        pessoas.adicionar(pj2);
        listaPessoas.forEach(System.out::println);

        // tentar criar PF e PJ com os mesmos identificadores
        PF pf3 = new PF("Bruno Pinho", "177.130.972-52");
        PJ pj3 = new PJ("Ada Tech", "41.846.322/0001-70");

        try {
            pessoas.adicionar(pf3);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            pessoas.adicionar(pj3);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

        // finalizando
        pessoas.deletarTodosOsCadastros();
    }
}

