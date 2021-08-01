package com.dio.live.dto;

import com.dio.live.model.Calendario;
import com.dio.live.model.Movimentacao;
import com.dio.live.model.Ocorrencia;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovimentacaoDTO implements ModelDTO<Movimentacao, MovimentacaoDTO> {

    private Long idMovimento;
    private Long idUsuario;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private BigDecimal periodo;
    private Long idOcorrencia;
    private Long idCalendario;

    @Override
    public MovimentacaoDTO toDTO(Movimentacao obj) {
        setIdMovimento(obj.getMovimentacaoId().getIdMovimento());
        setIdUsuario(obj.getMovimentacaoId().getIdUsuario());
        setDataEntrada(obj.getDataEntrada());
        setDataSaida(obj.getDataSaida());
        setPeriodo(obj.getPeriodo());
        if(obj.getOcorrencia() != null) {
            setIdOcorrencia(obj.getOcorrencia().getId());
        }
        if(obj.getCalendario() != null) {
            setIdCalendario(obj.getCalendario().getId());
        }

        return this;
    }

    @Override
    public Movimentacao toModel() {
        return new Movimentacao(
                new Movimentacao.MovimentacaoId(
                        this.getIdMovimento(),
                        this.getIdUsuario()
                ),
                this.getDataEntrada(),
                this.getDataSaida(),
                this.getPeriodo(),
                new Ocorrencia(
                        this.getIdOcorrencia(),
                        null, null
                ),
                new Calendario(
                        this.getIdCalendario(),
                        null, null, null
                )
        );
    }
}
