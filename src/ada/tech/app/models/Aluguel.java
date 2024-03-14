package ada.tech.app.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aluguel {
    private Integer repositoryID;
    private Pessoa pessoa;
    private Veiculo veiculo;
    private LocalDate dataInicio;
    private int duracaoEmDias;


    public double calcularValorTotal() {
        boolean isSUV = veiculo.getTipo() == TipoCarro.SUV;

        double valorDiaria = isSUV ? 200.0 : (veiculo.getTipo() == TipoCarro.MEDIO ? 150.0 : 100.0);

        double valorTotal = valorDiaria * duracaoEmDias;

        if (pessoa instanceof PF && duracaoEmDias > 5) {
            valorTotal *= 0.95;
        } else if (pessoa instanceof PJ && duracaoEmDias > 3) {
            valorTotal *= 0.90;
        }

        return valorTotal;
    }

    @Override
    public String toString() {
        return "Alugado o veiculo de placa %s para %s".formatted(veiculo.getPlaca(), pessoa.getNome());
    }

}

