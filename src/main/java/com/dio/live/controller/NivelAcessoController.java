package com.dio.live.controller;

import com.dio.live.model.NivelAcesso;
import com.dio.live.service.NivelAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/niveis_acesso")
public class NivelAcessoController {
    private NivelAcessoService service;

    @Autowired
    public NivelAcessoController(NivelAcessoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NivelAcesso> createNivel(@RequestBody NivelAcesso NivelAcesso) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(NivelAcesso)
        );
    }

    @GetMapping
    public ResponseEntity<List<NivelAcesso>> getNivelList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idNivel}")
    public ResponseEntity<NivelAcesso> getNivelByID(@PathVariable("idNivel") Long idNivel) {
        return ResponseEntity.ok(
                service.findById(idNivel)
        );
    }

    @PutMapping("/{idNivel}")
    public ResponseEntity<NivelAcesso> updateNivel(@PathVariable("idNivel") Long idNivel,
                                         @RequestBody NivelAcesso obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idNivel, obj)
        );
    }

    @DeleteMapping("/{idNivel}")
    public ResponseEntity deleteByID(@PathVariable("idNivel") Long idNivel) {

       service.delete(idNivel);

       return ResponseEntity.noContent().build();
    }
}
