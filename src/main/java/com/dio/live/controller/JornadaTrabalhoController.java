package com.dio.live.controller;

import com.dio.live.model.JornadaTrabalho;
import com.dio.live.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jornadas")
public class JornadaTrabalhoController {
    private JornadaService jornadaService;

    @Autowired
    public JornadaTrabalhoController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @PostMapping
    public ResponseEntity<JornadaTrabalho> createJornada(@RequestBody JornadaTrabalho jornadaTrabalho) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                jornadaService.save(jornadaTrabalho)
        );
    }

    @GetMapping
    public ResponseEntity<List<JornadaTrabalho>> getJornadaList() {
        return ResponseEntity.ok(
                jornadaService.findAll()
        );
    }

    @GetMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalho> getJornadaByID(@PathVariable("idJornada") Long idJornada) {
        return ResponseEntity.ok(
                jornadaService.findById(idJornada)
        );
    }

    @PutMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalho> updateJornada(@PathVariable("idJornada") Long idJornada,
                                         @RequestBody JornadaTrabalho obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                jornadaService.update(idJornada, obj)
        );
    }

    @DeleteMapping("/{idJornada}")
    public ResponseEntity deleteByID(@PathVariable("idJornada") Long idJornada) {

       jornadaService.delete(idJornada);

       return ResponseEntity.noContent().build();
    }
}
