package br.com.unhaeng.gestao_mottu.auth;

import br.com.unhaeng.gestao_mottu.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final UserService userService;

    public LoginListener( UserService userService ){
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent( AuthenticationSuccessEvent event ){
        var principal = event.getAuthentication().getPrincipal();
        if (principal instanceof OAuth2User oauth2User){
            userService.register(oauth2User);
        }
    }
}
