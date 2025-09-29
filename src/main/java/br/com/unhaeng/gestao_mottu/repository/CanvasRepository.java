package br.com.unhaeng.gestao_mottu.repository;

import br.com.unhaeng.gestao_mottu.model.Canvas;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {

    @EntityGraph(attributePaths = "filial")
    Optional<Canvas> findByFilial_FilialId(Long filialId);

}
