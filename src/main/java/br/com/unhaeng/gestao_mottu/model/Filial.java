package br.com.unhaeng.gestao_mottu.model;

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
    private Long filialId;

    @NotBlank(message = "{filial.name.notblank}")
    private String nome;

    @NotBlank(message = "{filial.cnpj.notblank}")
    @Size(min=14, max=14, message = "{filial.cnpj.size}")
    private String cnpj;

    @NotNull
    private boolean isActive = true;

    @Embedded
    private Endereco endereco;
}
