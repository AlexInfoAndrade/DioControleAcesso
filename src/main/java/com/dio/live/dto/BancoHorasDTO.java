package com.dio.live.dto;

import com.dio.live.model.BancoHoras;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BancoHorasDTO implements ModelDTO<BancoHoras, BancoHorasDTO> {

    private Long idBancoHoras;
    private Long idMovimentacao;
    private Long idUsuario;
    private LocalDateTime dataTrabalhada;
    private BigDecimal quantidadeHoras;
    private BigDecimal saldoHoras;

    @Override
    public BancoHorasDTO toDTO(BancoHoras obj) {
        setIdBancoHoras(obj.getBancoHorasId().getIdBancoHoras());
        setIdMovimentacao(obj.getBancoHorasId().getIdMovimentacao());
        setIdUsuario(obj.getBancoHorasId().getIdUsuario());
        setDataTrabalhada(obj.getDataTrabalhada());
        setQuantidadeHoras(obj.getQuantidadeHoras());
        setSaldoHoras(obj.getSaldoHoras());

        return this;
    }

    @Override
    public BancoHoras toModel() {
        return new BancoHoras(
                new BancoHoras.BancoHorasId(
                        this.getIdBancoHoras(),
                        this.getIdMovimentacao(),
                        this.getIdUsuario()
                ),
                this.getDataTrabalhada(),
                this.getQuantidadeHoras(),
                this.getSaldoHoras()
        );
    }
}
