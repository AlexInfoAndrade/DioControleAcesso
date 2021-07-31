package com.dio.live.controller;

import com.dio.live.model.Movimentacao;
import com.dio.live.service.MovimentacaoService;
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
    public ResponseEntity<Movimentacao> createMovimento(@RequestBody Movimentacao Movimentacao) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(Movimentacao)
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

    @PutMapping("/{idMovimento}/{idUsuario}")
    public ResponseEntity<Movimentacao> updateMovimento(
            @PathVariable("idMovimento") Long idMovimento,
            @PathVariable("idUsuario") Long idUsuario,
            @RequestBody Movimentacao obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idMovimento, idUsuario, obj)
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
