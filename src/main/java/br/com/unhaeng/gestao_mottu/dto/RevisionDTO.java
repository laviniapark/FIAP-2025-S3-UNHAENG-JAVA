package br.com.unhaeng.gestao_mottu.dto;

import br.com.unhaeng.gestao_mottu.model.Revision;

import java.time.Instant;
import java.time.LocalDateTime;

public class RevisionDTO {

    public record Create(Long canvasId, Long filialId, String shapesJson, String svgText) {}

    public record Response(Long id, Long canvasId, Long filialId, LocalDateTime createdAt) {
        public static Response of(Revision r){
            return new Response(
                    r.getRevisionId(),
                    r.getCanvas().getCanvasId(),
                    r.getFilial().getFilialId(),
                    r.getCreatedAt()
            );
        }
    }

    public record Detail(Long id, Long canvasId, Long filialId, LocalDateTime createdAt, String shapesJson, String svgText) {
        public static Detail of(Revision r){
            return new Detail(
                    r.getRevisionId(),
                    r.getCanvas().getCanvasId(),
                    r.getFilial().getFilialId(),
                    r.getCreatedAt(),
                    r.getShapesJson(),
                    r.getSvgText()
            );
        }
    }
}
