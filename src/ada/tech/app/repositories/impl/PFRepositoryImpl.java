package ada.tech.app.repositories.impl;

import ada.tech.app.models.PF;
import ada.tech.app.repositories.PFRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PFRepositoryImpl implements PFRepository {
    private final List<PF> listaPFs = new ArrayList<>();
    @Override
    public void criar(PF o) {
        boolean jaExiste = procuparPorIdentificador(o.getCPF()).isEmpty();
        if(!jaExiste){
            throw new RuntimeException("PF ja cadastrada!");
        }

        if (o.getRepositoryID() == null) {
            o.setRepositoryID(listaPFs.size());

            listaPFs.add(o);
        } else {
            PF substituir = listaPFs.get(o.getRepositoryID());
            listaPFs.remove(substituir);
            listaPFs.add(o.getRepositoryID(), o);
        }
    }

    @Override
    public void deletar(String cpf) {
        Optional<PF> pf = procuparPorIdentificador(cpf);
        if(pf.isEmpty()){
            System.out.println("PF nao encontrada!");
        } else{
            listaPFs.remove(pf.get());
            System.out.println(pf.get().getNome() + "removido(a).");
        }

    }

    @Override
    public List<PF> listarTodos() {
        return listaPFs;
    }

    @Override
    public Optional<PF> procuparPorIdentificador(String cpf) {
        return listaPFs.stream().filter(pf -> pf.getCPF().equals(cpf)).findFirst();
    }

    @Override
    public int tamanhoDaLista() {
        return listaPFs.size();
    }
}
