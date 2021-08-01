package com.dio.live.dto;

import com.dio.live.model.Calendario;
import com.dio.live.model.TipoData;
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
        setId(obj.getId());
        if(obj.getTipoData() != null) {
            setIdTipoData(obj.getTipoData().getId());
        }
        setDescricao(obj.getDescricao());
        setDataEspecial(obj.getDataEspecial());

        return this;
    }

    @Override
    public Calendario toModel() {
        return new Calendario(
                this.getId(),
                new TipoData(
                        this.getIdTipoData(),
                        null
                ),
                this.getDescricao(),
                this.getDataEspecial()
        );
    }
}
