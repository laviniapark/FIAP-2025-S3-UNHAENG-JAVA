package br.com.unhaeng.gestao_mottu.dto;

import br.com.unhaeng.gestao_mottu.model.Canvas;

public class CanvasDTO {

    public record Response(
            Long canvasId,
            Long filialId,
            String nome,
            Integer largura,
            Integer altura,
            String backgroundColor,
            Integer gridSize,
            Boolean snapAtivo,
            Boolean locked
    ){
        public static Response of(Canvas c){
            return new Response(
                    c.getCanvasId(),
                    c.getFilial().getFilialId(),
                    c.getNome(),
                    c.getLargura(),
                    c.getAltura(),
                    c.getBackgroundColor(),
                    c.getGridSize(),
                    c.getSnapAtivo(),
                    c.getLocked()
            );
        }
    }
}
