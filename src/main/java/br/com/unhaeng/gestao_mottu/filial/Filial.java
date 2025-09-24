package br.com.unhaeng.gestao_mottu.filial;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="T_FILIAIS")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    private String nome;

    @NotBlank(message = "")
    private String cnpj;

    @NotNull
    private boolean isActive = true;

    @Embedded
    private Endereco endereco;
}
