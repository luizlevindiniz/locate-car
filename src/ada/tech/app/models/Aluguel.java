package ada.tech.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Aluguel {
    /* Infelizmente, o lombok não tem uma opcao para escolher quais campos entrar no builder. O builder
     * ira mostrar todos os campos, mesmo que nao queiramos expor o repositoryID. Ignorar que e possivel construir
     * um objeto ja definindo o repositoryID ao inves dele ser automaticamente definido quando adicionado na lista
     * de alugueis.  */
    private Integer repositoryID;
    private Pessoa pessoa;
    private Veiculo veiculo;
    private LocalDateTime dataInicio; //Uso de localdatetime devido já utilizar data e hora
    private LocaisAluguel locaisAluguel; //Criando enum para limitar locais de aluguel

    @Override
    public String toString() {
        return "Alugado o veiculo de placa %s para %s na %s".formatted(veiculo.getPlaca(), pessoa.getNome(), locaisAluguel);
    }

}

