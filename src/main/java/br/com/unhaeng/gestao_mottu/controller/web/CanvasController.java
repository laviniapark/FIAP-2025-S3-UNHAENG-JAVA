package br.com.unhaeng.gestao_mottu.controller.web;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.dto.CanvasDTO;
import br.com.unhaeng.gestao_mottu.service.CanvasService;
import br.com.unhaeng.gestao_mottu.service.ElementoService;
import br.com.unhaeng.gestao_mottu.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/canvas")
@RequiredArgsConstructor
public class CanvasController {

    private final CanvasService canvasService;
    private final ElementoService elementoService;
    private final FilialService filialService;
    private final MessageHelper messageHelper;

    @GetMapping
    public String selectFilial(Model model) {
        model.addAttribute("filiais", filialService.getAllFiliais());
        return "canvas/select";
    }

    // Recebe o ID da filial, cria/recupera Canvas e redireciona pro editor
    @PostMapping("/select")
    public String postSelectFilial(@RequestParam Long filialId, RedirectAttributes redirect) {
        var canvas = canvasService.getOrCreateDefaultForFilial(filialId);
        redirect.addFlashAttribute("flash", messageHelper.get("canvas.ready"));
        return "redirect:/canvas/" + canvas.getCanvasId() + "/edit";
    }

    @GetMapping("/{canvasId}/edit")
    public String edit(@PathVariable Long canvasId, Model model) {
        var c = canvasService.getById(canvasId);
        var dto = CanvasDTO.Response.of(c);

        var elementos = elementoService.listByCanvas(canvasId);

        model.addAttribute("canvas", dto);
        model.addAttribute("elementos", elementos);
        return "canvas/edit";
    }

}
