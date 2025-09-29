package br.com.unhaeng.gestao_mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "T_ELEMENTOS")
public class Elemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="elemento_id")
    private Long elementoId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "canvas_id", nullable = false)
    private Canvas canvas;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private ElementoTipo tipo;

    @NotNull
    @Column(name = "z_index", nullable = false)
    @Builder.Default
    private Integer zIndex = 0;

    /**
     * Props em JSON (CLOB) por tipo:
     *
     * LINE:
     *   { "x1":int, "y1":int, "x2":int, "y2":int,
     *     "stroke":"#111", "strokeWidth":2, "orthogonal":false }
     *
     * LABEL:
     *   { "x":int, "y":int,
     *     "text":"Vagas A",
     *     "fontSize":12, "fontWeight":"bold", "fill":"#222" }
     *
     * ANCHOR:
     *   { "x":int, "y":int,
     *     "name":"A1", "kind":"moto", "rotation":0 }
     *
     * Observação: o front desenha o ícone da moto sobre o ANCHOR.
     */

    @Lob
    @Column(name = "props_json", nullable = false, columnDefinition = "CLOB")
    @NotBlank
    private String propsJson;
}
