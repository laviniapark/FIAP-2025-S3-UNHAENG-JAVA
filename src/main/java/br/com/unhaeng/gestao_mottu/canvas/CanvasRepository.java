package br.com.unhaeng.gestao_mottu.canvas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {
    Optional<Canvas> findByName(String nome);
}
