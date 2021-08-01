package com.dio.live.controller;

import com.dio.live.dto.MovimentacaoDTO;
import com.dio.live.model.Movimentacao;
import com.dio.live.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentos")
public class MovimentacaoController {
    private MovimentacaoService service;

    @Autowired
    public MovimentacaoController(MovimentacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Movimentacao> createMovimento(@RequestBody MovimentacaoDTO movimentacaoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(movimentacaoDTO.toModel())
        );
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> getMovimentoList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idMovimento}/{idUsuario}")
    public ResponseEntity<Movimentacao> getMovimentoByID(
            @PathVariable("idMovimento") Long idMovimento,
            @PathVariable("idUsuario") Long idUsuario) {
        
        return ResponseEntity.ok(
                service.findById(idMovimento, idUsuario)
        );
    }

    @PutMapping("/")
    public ResponseEntity<Movimentacao> updateMovimento(@RequestBody MovimentacaoDTO movimentacaoDTO) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(
                        movimentacaoDTO.getIdMovimento(),
                        movimentacaoDTO.getIdUsuario(),
                        movimentacaoDTO.toModel()
                )
        );
    }

    @DeleteMapping("/{idMovimento}/{idUsuario}")
    public ResponseEntity deleteByID(
            @PathVariable("idMovimento") Long idMovimento,
            @PathVariable("idUsuario") Long idUsuario) {

       service.delete(idMovimento, idUsuario);

       return ResponseEntity.noContent().build();
    }
}
