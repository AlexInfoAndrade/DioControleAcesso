package com.dio.live.repository;

import com.dio.live.model.JornadaTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JornadaRepository extends JpaRepository<JornadaTrabalho, Long> {

    @Transactional(readOnly = true)
    Optional<JornadaTrabalho> findByDescricaoContainingIgnoreCase(String descricao);
}
