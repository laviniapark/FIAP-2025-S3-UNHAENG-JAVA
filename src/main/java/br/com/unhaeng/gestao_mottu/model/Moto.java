package br.com.unhaeng.gestao_mottu.model;

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
    @Column(name="moto_id")
    private Long motoId;

    @NotBlank(message = "{moto.placa.notblank}")
    @Size(min = 7, max = 7, message = "{moto.placa.size}")
    private String placa;

    @NotBlank(message = "{moto.marca.notblank}")
    private String marca;

    @NotBlank(message = "{moto.modelo.notblank}")
    private String modelo;

    @NotNull(message = "{moto.ano.notnull}")
    @Min(value = 1990, message = "{moto.ano.min}")
    private Integer ano;

    @NotNull(message = "{moto.status.notnull}")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;
}
