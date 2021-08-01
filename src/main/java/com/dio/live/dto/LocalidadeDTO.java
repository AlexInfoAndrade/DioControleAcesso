package com.dio.live.dto;

import com.dio.live.model.Localidade;
import com.dio.live.model.NivelAcesso;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LocalidadeDTO implements ModelDTO<Localidade, LocalidadeDTO> {

    private Long id;
    private Long idNivelAcesso;
    private String descricao;

    @Override
    public LocalidadeDTO toDTO(Localidade obj) {
        setId(obj.getId());
        if(obj.getNivelAcesso() != null) {
            setIdNivelAcesso(obj.getNivelAcesso().getId());
        }
        setDescricao(obj.getDescricao());

        return this;
    }

    @Override
    public Localidade toModel() {
        return new Localidade(
                this.getId(),
                new NivelAcesso(
                        this.getIdNivelAcesso(),
                        null
                ),
                this.getDescricao()
        );
    }
}
