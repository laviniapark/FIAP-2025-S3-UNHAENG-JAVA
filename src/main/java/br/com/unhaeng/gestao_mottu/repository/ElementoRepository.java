package br.com.unhaeng.gestao_mottu.repository;

import br.com.unhaeng.gestao_mottu.model.Elemento;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElementoRepository extends JpaRepository<Elemento, Long> {

    @EntityGraph(attributePaths = "canvas")
    List<Elemento> findByCanvas_CanvasId(Long canvasId, Sort sort);
}
