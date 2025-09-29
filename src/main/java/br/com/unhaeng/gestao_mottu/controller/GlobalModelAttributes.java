package br.com.unhaeng.gestao_mottu.controller;

import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.service.FilialService;
import br.com.unhaeng.gestao_mottu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {
    private final UserService userService;
    private final FilialService  filialService;

    @ModelAttribute("user")
    public Object user(@AuthenticationPrincipal OAuth2User principal) {
        return (principal != null) ? userService.register(principal) : null;
    }

    @ModelAttribute("filiais")
    public List<Filial> filiais() {
        return filialService.getAllFiliais();
    }
}
