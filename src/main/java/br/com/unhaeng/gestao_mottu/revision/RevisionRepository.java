package br.com.unhaeng.gestao_mottu.revision;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends JpaRepository<Revision, Long> {
}
