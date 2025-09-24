package br.com.unhaeng.gestao_mottu.canvas;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_CANVAS")
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "canvas_id")
    private Long canvasId;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false)
    private int largura;
    @Column(nullable = false)
    private int altura;
    @Column(length = 16)
    private String backgroundColor;
}
