package br.com.unhaeng.gestao_mottu.repository;

import br.com.unhaeng.gestao_mottu.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RevisionRepository extends JpaRepository<Revision, Long> {

    // Retorna todas as revisoes do Canvas (ordenada da mais nova para a mais antiga)
    List<Revision> findByCanvas_CanvasIdOrderByCreatedAtDesc(Long canvasId);

    // Retorna apenas a revisao mais recente
    Optional<Revision> findTopByCanvas_CanvasIdOrderByCreatedAtDesc(Long canvasId);

    // Retorna apenas o número de revisões existentes para um canvas
    long countByCanvas_CanvasId( Long canvasId);

    // Retorna todas as revisões de um canvas (ordenada da mais antiga para a mais nova)
    List<Revision> findByCanvas_CanvasIdOrderByCreatedAtAsc(Long canvasId);
}
