package com.dio.live.repository;

import com.dio.live.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    Optional<Calendario> findByTipoDataIdAndDataEspecial(Long idTipoData, LocalDateTime dataEspecial);
}
