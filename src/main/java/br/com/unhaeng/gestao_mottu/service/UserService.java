package br.com.unhaeng.gestao_mottu.service;

import br.com.unhaeng.gestao_mottu.model.User;
import br.com.unhaeng.gestao_mottu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User register(OAuth2User principal){
        String email = principal.getAttribute("email");
        return repository.findByEmail(email)
                .orElseGet(() -> repository.save(new User(principal)));
    }
}
