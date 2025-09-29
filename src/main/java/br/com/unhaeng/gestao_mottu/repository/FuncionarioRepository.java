package br.com.unhaeng.gestao_mottu.repository;

import br.com.unhaeng.gestao_mottu.model.Funcionario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @EntityGraph(attributePaths = "filial")
    List<Funcionario> findAll();

    @EntityGraph(attributePaths = "filial")
    Optional<Funcionario> findById(Long id);
}
