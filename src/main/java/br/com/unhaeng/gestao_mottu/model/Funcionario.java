package br.com.unhaeng.gestao_mottu.model;

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
    @Column(name="funcionario_id")
    private Long funcionarioId;

    @NotBlank(message = "{funcionario.nome.notblank}")
    private String nomeCompleto;

    @NotBlank(message = "{funcionario.cpf.notblank}")
    @Size(min=11, max=11, message = "{funcionario.cpf.size}")
    private String cpf;

    @NotNull(message = "{funcionario.cargo.notnull}")
    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    private boolean isActive= true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filial_id")
    private Filial filial;

}
