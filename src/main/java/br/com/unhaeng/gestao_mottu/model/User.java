package br.com.unhaeng.gestao_mottu.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="T_UNHAENG_USERS")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String avatarUrl;

    @CreationTimestamp
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    public User(OAuth2User principal) {
        this.name      = firstNonBlankAttr(principal, "name", "login", "preferred_username", "given_name");
        this.email     = firstNonBlankAttr(principal, "email");
        this.avatarUrl = firstNonBlankAttr(principal, "avatar", "picture", "avatar_url");
    }

    // Busca o primeiro atributo não nulo/não vazio do principal entre as chaves fornecidas
    private static String firstNonBlankAttr(OAuth2User p, String... keys) {
        for (String k : keys) {
            Object v = p.getAttribute(k);
            if (v != null) {
                String s = String.valueOf(v).trim();
                if (!s.isEmpty()) return s;
            }
        }
        return null;
    }
}
