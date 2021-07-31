package com.dio.live.controller;

import com.dio.live.model.Ocorrencia;
import com.dio.live.service.OcorrenciaService;
import com.dio.live.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {
    private OcorrenciaService service;

    @Autowired
    public OcorrenciaController(OcorrenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ocorrencia> createOcorrencia(@RequestBody Ocorrencia Ocorrencia) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(Ocorrencia)
        );
    }

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> getOcorrenciaList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idOcorrencia}")
    public ResponseEntity<Ocorrencia> getOcorrenciaByID(@PathVariable("idOcorrencia") Long idOcorrencia) {
        return ResponseEntity.ok(
                service.findById(idOcorrencia)
        );
    }

    @PutMapping("/{idOcorrencia}")
    public ResponseEntity<Ocorrencia> updateOcorrencia(@PathVariable("idOcorrencia") Long idOcorrencia,
                                         @RequestBody Ocorrencia obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idOcorrencia, obj)
        );
    }

    @DeleteMapping("/{idOcorrencia}")
    public ResponseEntity deleteByID(@PathVariable("idOcorrencia") Long idOcorrencia) {

       service.delete(idOcorrencia);

       return ResponseEntity.noContent().build();
    }
}
