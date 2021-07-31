package com.dio.live.repository;

import com.dio.live.model.CategoriaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoriaUsuarioRepository extends JpaRepository<CategoriaUsuario, Long> {

    @Transactional(readOnly = true)
    Optional<CategoriaUsuario> findByDescricaoContainingIgnoreCase(String descricao);
}
