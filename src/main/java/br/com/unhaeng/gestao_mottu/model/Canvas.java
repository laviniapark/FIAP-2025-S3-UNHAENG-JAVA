package br.com.unhaeng.gestao_mottu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="T_CANVAS")
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "canvas_id")
    private Long canvasId;

    @Column(nullable = false, length = 120, unique = true)
    private String nome;

    @NotNull @Min(1) @Max(10000) @Column(nullable = false)
    private Integer largura;

    @NotNull @Min(1) @Max(10000) @Column(nullable = false)
    private Integer altura;

    @Column(length = 16)
    private String backgroundColor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @NotNull @Min(1) @Max(200)
    @Column(name = "grid_size", nullable = false)
    @Builder.Default
    private Integer gridSize = 10;
    // Garante alinhamento reto, para evitar elementos tortos

    @NotNull
    @Column(name = "snap_ativo", nullable = false)
    @Builder.Default
    private Boolean snapAtivo = Boolean.TRUE;
    // TRUE = todas as alteraçoes de posicao/tamanho dos elementos passam pelo ajuste do gridSize

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Boolean locked = Boolean.FALSE;
    // Diz se o canvas inteiro está bloqueado ou nao para ediçao

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Elemento> elementos = new ArrayList<>();
    // Lista de formas e objetos desenhados no Canvas
    // Um canvas pode ter varios elementos

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Revision> revisoes = new ArrayList<>();
    // Guarda o historico de versoes (snapshots)
    // O usuario termina um layout, clica "Salvar Revisao" > o estado inteiro vira uma Revision
}
