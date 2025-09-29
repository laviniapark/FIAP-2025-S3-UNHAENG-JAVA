package br.com.unhaeng.gestao_mottu.repository;

import br.com.unhaeng.gestao_mottu.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
