package br.com.unhaeng.gestao_mottu.revision;

import br.com.unhaeng.gestao_mottu.canvas.Canvas;
import br.com.unhaeng.gestao_mottu.filial.Filial;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="T_REVISIONS")
public class Revision {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revisionId;

    @ManyToOne
    @JoinColumn(name="filial_id")
    private Filial filial;

    @ManyToOne
    @JoinColumn(name="canvas_id")
    private Canvas canvas;

    private Instant createdAt = Instant.now();

    @Lob
    private String shapesJson;
    @Lob
    private String svgText;
}
