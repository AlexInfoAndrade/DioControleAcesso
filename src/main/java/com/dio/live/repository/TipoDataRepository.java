package com.dio.live.repository;

import com.dio.live.model.TipoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoDataRepository extends JpaRepository<TipoData, Long> {

    Optional<TipoData> findByDescricaoContainingIgnoreCase(String descricao);
}
