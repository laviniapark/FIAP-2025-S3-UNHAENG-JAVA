package br.com.unhaeng.gestao_mottu.dto;

import br.com.unhaeng.gestao_mottu.model.Elemento;
import br.com.unhaeng.gestao_mottu.model.ElementoTipo;

public class ElementoDTO {

    public static record Response(Long id, ElementoTipo tipo, Integer zIndex, String propsJson) {
        public static Response of(Elemento e) {
            return new Response(
                    e.getElementoId(),
                    e.getTipo(),
                    e.getZIndex(),
                    e.getPropsJson()
            );
        }
    }

    public static record CreateUpdate(ElementoTipo tipo, Integer zIndex, String propsJson) {}

    public static record Move(Integer x, Integer y) {}
}

