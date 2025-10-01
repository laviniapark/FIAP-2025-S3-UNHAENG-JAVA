package br.com.unhaeng.gestao_mottu.controller.web;

import br.com.unhaeng.gestao_mottu.config.MessageHelper;
import br.com.unhaeng.gestao_mottu.model.Funcionario;
import br.com.unhaeng.gestao_mottu.service.FilialService;
import br.com.unhaeng.gestao_mottu.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final MessageHelper messageHelper;
    private final FilialService filialService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("funcionarios", funcionarioService.getAllFuncionarios());
        return "funcionario/index";
    }

    @GetMapping("/form")
    public String form (Funcionario funcionario){
        return "funcionario/form";
    }

    @PostMapping
    public String create(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes redirect){
        if( result.hasErrors() ) return "funcionario/form";

        funcionarioService.create(funcionario);
        redirect.addFlashAttribute("message", messageHelper.get("funcionario.create.success"));
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Funcionario db = funcionarioService.getFuncionarioById(id);
        model.addAttribute("funcionario", db);
        return "funcionario/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @Valid Funcionario funcionario, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "funcionario/form";

        funcionarioService.update(id, funcionario);
        redirect.addFlashAttribute("message", messageHelper.get("funcionario.update.success"));
        return "redirect:/funcionarios";
    }

    @DeleteMapping("/{id}")
    public String deactivateEmployee(@PathVariable Long id, RedirectAttributes redirect){
        funcionarioService.deactivate(id);
        redirect.addFlashAttribute("message", messageHelper.get("funcionario.deactivate.success"));
        return "redirect:/funcionarios";
    }
}
