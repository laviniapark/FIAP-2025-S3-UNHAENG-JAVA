package br.com.unhaeng.gestao_mottu.funcionario;

import br.com.unhaeng.gestao_mottu.filial.Filial;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Table(name = "T_FUNCIONARIOS")
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String cpf;

    @NotNull
    private CargoEnum cargo;

    @NotNull
    private boolean isActive= true;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;

}
