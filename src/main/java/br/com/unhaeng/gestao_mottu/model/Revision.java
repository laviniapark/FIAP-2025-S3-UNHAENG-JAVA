package br.com.unhaeng.gestao_mottu.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="T_REVISIONS",
        indexes = {
                @Index(name="ix_revision_canvas_created", columnList = "canvas_id, created_at DESC")
        })
public class Revision {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="revision_id")
    private Long revisionId;

    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name="filial_id", nullable = false)
    private Filial filial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="canvas_id", nullable = false)
    private Canvas canvas;

    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        if (createdAt == null) createdAt =  LocalDateTime.now();
    }

    @Lob
    private String shapesJson;
    @Lob
    private String svgText;

}
