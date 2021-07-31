package com.dio.live.dto;

import com.dio.live.model.Calendario;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CalendarioDTO implements ModelDTO<Calendario, CalendarioDTO> {

    private Long id;
    private Long idTipoData;
    private String descricao;
    private LocalDateTime dataEspecial;

    @Override
    public CalendarioDTO toDTO(Calendario obj) {
        return null;
    }

    @Override
    public Calendario toModel() {
        return null;
    }
}
