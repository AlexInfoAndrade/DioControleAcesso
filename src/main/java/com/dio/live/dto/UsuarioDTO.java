package com.dio.live.dto;

import com.dio.live.model.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsuarioDTO implements ModelDTO<Usuario, UsuarioDTO> {

    private Long id;
    private Long idCategoriaUsuario;
    private String nome;
    private Long idEmpresa;
    private Long idNivelAcesso;
    private Long idJornadaTrabalho;
    private BigDecimal tolerancia;
    private LocalDateTime inicioJornada;
    private LocalDateTime finalJornada;

    @Override
    public UsuarioDTO toDTO(Usuario obj) {
        setId(obj.getId());
        if(obj.getCategoriaUsuario() != null) {
            setIdCategoriaUsuario(obj.getCategoriaUsuario().getId());
        }
        setNome(obj.getNome());
        if(obj.getEmpresa() != null) {
            setIdEmpresa(obj.getEmpresa().getId());
        }
        if(obj.getNivelAcesso() != null) {
            setIdNivelAcesso(obj.getNivelAcesso().getId());
        }
        if(obj.getJornadaTrabalho() != null) {
            setIdJornadaTrabalho(obj.getJornadaTrabalho().getId());
        }
        setTolerancia(obj.getTolerancia());
        setInicioJornada(obj.getInicioJornada());
        setFinalJornada(obj.getFinalJornada());

        return this;
    }

    @Override
    public Usuario toModel() {
        return new Usuario(
                this.getId(),
                new CategoriaUsuario(
                        this.getIdCategoriaUsuario(),
                        null
                ),
                this.getNome(),
                new Empresa(
                        this.getIdEmpresa(),
                        null, null, null, null,
                        null, null, null
                ),
                new NivelAcesso(
                        this.getIdNivelAcesso(),
                        null
                ),
                new JornadaTrabalho(
                        this.getIdJornadaTrabalho(),
                        null
                ),
                this.getTolerancia(),
                this.getInicioJornada(),
                this.getFinalJornada()
        );
    }
}
