package ada.tech.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Aluguel {
    private Integer repositoryID;
    private Pessoa pessoa;
    private Veiculo veiculo;
    private LocalDate dataInicio;

    @Override
    public String toString() {
        return "Alugado o veiculo de placa %s para %s".formatted(veiculo.getPlaca(), pessoa.getNome());
    }

}

