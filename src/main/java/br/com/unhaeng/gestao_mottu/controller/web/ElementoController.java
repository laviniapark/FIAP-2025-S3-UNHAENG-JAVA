package br.com.unhaeng.gestao_mottu.controller.web;

import br.com.unhaeng.gestao_mottu.dto.ElementoDTO;
import br.com.unhaeng.gestao_mottu.service.ElementoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/elementos")
@RequiredArgsConstructor
public class ElementoController {

    private final ElementoService service;

    // Listar os elementos de um canvas
    @GetMapping("/by-canvas/{canvasId}")
    @ResponseBody // Retorno vai virar JSON na resposta HTTP
    public List<ElementoDTO.Response> listByCanvas(@PathVariable Long canvasId){
        return service.listByCanvas(canvasId);
    }

    // Criar elemento
    @PostMapping("/canvas/{canvasId}")
    @ResponseBody
    public ResponseEntity<ElementoDTO.Response> create(@PathVariable Long canvasId,
                                              @Valid @RequestBody ElementoDTO.CreateUpdate dto){
        return ResponseEntity.ok(service.create(canvasId, dto));
    }

    // Atualizar elemento
    @PutMapping("/{elementoId}")
    @ResponseBody
    public ResponseEntity<ElementoDTO.Response> update(@PathVariable Long elementoId,
                                              @Valid @RequestBody ElementoDTO.CreateUpdate dto){
        return ResponseEntity.ok(service.update(elementoId, dto));
    }

    // Mover posiçao do elemento
    @PatchMapping("/{elementoId}/move")
    @ResponseBody
    public ResponseEntity<ElementoDTO.Response> move(@PathVariable Long elementoId,
                                            @Valid @RequestBody ElementoDTO.Move dto){
        return ResponseEntity.ok(service.move(elementoId, dto));
    }

    // Apagar elemento
    @DeleteMapping("/{elementoId}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long elementoId){
        service.delete(elementoId);
        return ResponseEntity.noContent().build();
    }
}
