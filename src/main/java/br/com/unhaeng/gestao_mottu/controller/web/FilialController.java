package br.com.unhaeng.gestao_mottu.controller.web;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.service.FilialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/filiais")
@RequiredArgsConstructor
public class FilialController {

    private final FilialService filialService;
    private final MessageHelper messageHelper;

    @GetMapping
    public String index(Model model){
        model.addAttribute("filiais", filialService.getAllFiliais());
        return "filial/index";
    }

    @GetMapping("/form")
    public String form(Filial filial, Model model){
        return "filial/form";
    }

    @PostMapping
    public String create(@Valid Filial filial, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "filial/form";

        filialService.create(filial);
        redirect.addFlashAttribute("message", messageHelper.get("filial.create.success"));
        return "redirect:/filiais";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Filial db = filialService.getFilialById(id);
        model.addAttribute("filial", db);
        return "filial/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Filial filial, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "filial/form";

        filialService.update(id, filial);
        redirect.addFlashAttribute("message", messageHelper.get("filial.update.success"));
        return "redirect:/filiais";
    }

    @DeleteMapping("/{id}")
    public String deactivateBranch(@PathVariable Long id, RedirectAttributes redirect){
        filialService.deactivate(id);
        redirect.addFlashAttribute("message", messageHelper.get("filial.deactivate.success"));
        return  "redirect:/filiais";
    }
}
