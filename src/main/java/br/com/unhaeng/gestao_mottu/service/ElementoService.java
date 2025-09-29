package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.dto.ElementoDTO;
import br.com.unhaeng.gestao_mottu.model.Canvas;
import br.com.unhaeng.gestao_mottu.model.Elemento;
import br.com.unhaeng.gestao_mottu.repository.CanvasRepository;
import br.com.unhaeng.gestao_mottu.repository.ElementoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementoService {

    private final ElementoRepository elementoRepository;
    private final CanvasRepository canvasRepository;
    // Converte JSON <-> Map<String,Object> (mapa chave valor)
    private final ObjectMapper objectMapper;
    private final MessageHelper messageHelper;

    // Busca todos os elementos de um Canvas
    public List<ElementoDTO.Response> listByCanvas(Long canvasId){
        var list = elementoRepository.findByCanvas_CanvasId(
                canvasId, Sort.by(Sort.Order.asc("zIndex"), Sort.Order.asc("elementoId")));
        // Converte entidades em DTOs
        return list.stream().map(ElementoDTO.Response::of).toList();
    }

    // Cria um novo elemento dentro do Canvas
    @Transactional
    public ElementoDTO.Response create(Long canvasId, ElementoDTO.CreateUpdate dto){
        Canvas canvas = canvasRepository.findById(canvasId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("canvas.notfound")));

        String snapped = snapPropsJson(canvas, dto.propsJson());
        var e = Elemento.builder()
                .canvas(canvas)
                .tipo(dto.tipo())
                .zIndex(dto.zIndex())
                .propsJson(snapped)
                .build();

        e = elementoRepository.save(e);
        return ElementoDTO.Response.of(e);
    }

    // Atualiza tipo/zindex/propsJson de um elemento existente
    @Transactional
    public ElementoDTO.Response update(Long elementoId, ElementoDTO.CreateUpdate dto){
        var e = elementoRepository.findById(elementoId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("elemento.notfound")));

        String snapped = snapPropsJson(e.getCanvas(), dto.propsJson());
        e.setTipo(dto.tipo());
        e.setZIndex(dto.zIndex());
        e.setPropsJson(snapped);

        e = elementoRepository.save(e);
        return ElementoDTO.Response.of(e);
    }

    @Transactional
    public void delete(Long elementoId){
        if (!elementoRepository.existsById(elementoId)) return;
        elementoRepository.deleteById(elementoId);
    }

    // Move rapidamente um elemento alterando apenas campos de posição no propsJson
    @Transactional
    public ElementoDTO.Response move(Long elementoId, ElementoDTO.Move dto){
        // Carrega elemento e canvas pra saber gridSize e snapAtivo
        var e = elementoRepository.findById(elementoId)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.get("elemento.notfound")));
        var c = e.getCanvas();

        // Carrega JSON, aplica snap nos campos de posição e salva
        Map<String, Object> props = readProps(e.getPropsJson());
        if (dto.x() != null) props.put("x", snap(c, dto.x()));
        if (dto.y() != null) props.put("y", snap(c, dto.y()));

        // também tenta snap em nomes comuns de LINE
        maybeSnap(props, "x1", c);
        maybeSnap(props, "y1", c);
        maybeSnap(props, "x2", c);
        maybeSnap(props, "y2", c);

        e.setPropsJson(writeProps(props));
        e = elementoRepository.save(e);
        return ElementoDTO.Response.of(e);
    }

    // HELPERS

    private Map<String, Object> readProps(String json){
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
        } catch (Exception ex){
            throw new IllegalArgumentException(messageHelper.get("propsjson.invalid"));
        }
    }

    private String writeProps(Map<String,Object> m){
        try {
            return objectMapper.writeValueAsString(m);
        } catch (Exception ex){
            throw new IllegalStateException(messageHelper.get("propsjson.ser.error"));
        }
    }

    private void maybeSnap(Map<String,Object> m, String key, Canvas c){
        if (m.containsKey(key) && m.get(key) instanceof Number n){
            m.put(key, snap(c, n.intValue()));
        }
    }

    private String snapPropsJson(Canvas c, String json){
        if (c.getSnapAtivo() == null || !c.getSnapAtivo()) return json;
        Map<String,Object> m = readProps(json);
        // campos comuns
        maybeSnap(m, "x", c);
        maybeSnap(m, "y", c);
        maybeSnap(m, "x1", c);
        maybeSnap(m, "y1", c);
        maybeSnap(m, "x2", c);
        maybeSnap(m, "y2", c);
        return writeProps(m);
    }

    // Arredonda o valor v para a grade do canvas
    private int snap(Canvas c, int v){
        if (c.getSnapAtivo() == null || !c.getSnapAtivo()) return v;
        // Define o default como 10
        int g = Optional.ofNullable(c.getGridSize()).orElse(10);
        // Proibe zero e negativo
        g = Math.max(1, g);
        // Arredonda para o multiplo mais proximo de g
        return Math.round((float)v / g) * g;
    }
}
