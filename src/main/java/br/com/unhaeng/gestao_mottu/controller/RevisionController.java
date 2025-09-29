package br.com.unhaeng.gestao_mottu.controller;

import br.com.unhaeng.gestao_mottu.dto.RevisionDTO;
import br.com.unhaeng.gestao_mottu.service.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/revisions")
public class RevisionController {

    private final RevisionService revisionService;

    // Criar snapshot (chamado pelo front ao clicar "Salvar versão")
    @PostMapping
    @ResponseBody
    public ResponseEntity<RevisionDTO.Response> create(@RequestBody RevisionDTO.Create dto){
        var saved = revisionService.saveSnapshot(dto);
        return ResponseEntity.ok(RevisionDTO.Response.of(saved));
    }

    // Listar por canvas
    @GetMapping("/by-canvas/{canvasId}")
    @ResponseBody
    public ResponseEntity<?> listByCanvas(@PathVariable Long canvasId){
        var list = revisionService.listByCanvas(canvasId).stream()
                .map(RevisionDTO.Response::of).toList();
        return ResponseEntity.ok(list);
    }

    // Pegar última revisão
    @GetMapping("/latest/{canvasId}")
    @ResponseBody
    public ResponseEntity<RevisionDTO.Detail> latest(@PathVariable Long canvasId){
        var r = revisionService.getLatest(canvasId);
        return ResponseEntity.ok(RevisionDTO.Detail.of(r));
    }

    // Restaurar uma revisão (gera uma nova revisão clonada)
    @PostMapping("/{revisionId}/restore")
    @ResponseBody
    public ResponseEntity<RevisionDTO.Response> restore(@PathVariable Long revisionId){
        var r = revisionService.restoreFrom(revisionId);
        return ResponseEntity.ok(RevisionDTO.Response.of(r));
    }

    // VIEW = página de histórico do canvas
    @GetMapping("/history/{canvasId}")
    public String historyPage(@PathVariable Long canvasId, Model model){
        var revisions = revisionService.listByCanvas(canvasId).stream()
                .map(RevisionDTO.Response::of).toList();
        model.addAttribute("canvasId", canvasId);
        model.addAttribute("revisions", revisions);
        return "revisions/history"; // crie templates/revisions/history.html
    }
}
