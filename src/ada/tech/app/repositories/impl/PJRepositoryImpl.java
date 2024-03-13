package ada.tech.app.repositories.impl;

import ada.tech.app.models.PJ;
import ada.tech.app.repositories.PJRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PJRepositoryImpl implements PJRepository {
    private final List<PJ> listaPJs = new ArrayList<>();
    @Override
    public void criar(PJ o) {
        boolean jaExiste = procuparPorIdentificador(o.getCNPJ()).isEmpty();
        if(!jaExiste){
            throw new RuntimeException("PJ ja cadastrada!");
        }

        if (o.getRepositoryID() == null) {
            o.setRepositoryID(listaPJs.size());

            listaPJs.add(o);
        } else {
            PJ substituir = listaPJs.get(o.getRepositoryID());
            listaPJs.remove(substituir);
            listaPJs.add(o.getRepositoryID(), o);
        }
    }

    @Override
    public void deletar(String cnpj) {
        Optional<PJ> pj = procuparPorIdentificador(cnpj);
        if(pj.isEmpty()){
            System.out.println("PJ nao encontrada!");
        } else{
            listaPJs.remove(pj.get());
            System.out.println(pj.get().getNome() + "removido(a).");
        }

    }

    @Override
    public List<PJ> listarTodos() {
        return listaPJs;
    }

    @Override
    public Optional<PJ> procuparPorIdentificador(String cnpj) {
        return listaPJs.stream().filter(pj -> pj.getCNPJ().equals(cnpj)).findFirst();
    }

    @Override
    public int tamanhoDaLista() {
        return listaPJs.size();
    }
}
