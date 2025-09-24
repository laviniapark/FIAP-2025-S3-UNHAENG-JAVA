package br.com.unhaeng.gestao_mottu.moto;

import br.com.unhaeng.gestao_mottu.filial.Filial;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_MOTOS")

public class Moto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    @NotBlank(message = "")
    @Size(min = 7, max = 7, message = "")
    private String placa;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(value = 1990, message = "")
    private int ano;

    @NotNull
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;
}
