package br.com.unhaeng.gestao_mottu.controller.web;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Moto;
import br.com.unhaeng.gestao_mottu.service.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService motoService;
    private final MessageHelper  messageHelper;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("motos", motoService.getAllMotos());
        return "moto/index";
    }

    @GetMapping("/form")
    public String form(Moto moto) {
        return "moto/form";
    }

    @PostMapping
    public String create(@Valid Moto moto, BindingResult result, RedirectAttributes redirect){
        if( result.hasErrors() ) return "moto/form";

        motoService.create(moto);
        redirect.addFlashAttribute("message", messageHelper.get("moto.create.success"));
        return "redirect:/motos";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Moto db = motoService.getMotoById(id);
        model.addAttribute("moto", db);
        return "moto/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Moto moto, BindingResult result, RedirectAttributes redirect){
        if( result.hasErrors() ) return "moto/form";

        motoService.update(id, moto);
        redirect.addFlashAttribute("message", messageHelper.get("moto.update.success"));
        return "redirect:/motos";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        motoService.deleteById(id);
        redirect.addFlashAttribute("message", messageHelper.get("moto.delete.success"));
        return "redirect:/motos";
    }
}
