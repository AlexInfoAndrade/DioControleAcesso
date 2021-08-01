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
    private JornadaService service;

    @Autowired
    public JornadaTrabalhoController(JornadaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JornadaTrabalho> createJornada(@RequestBody JornadaTrabalho jornadaTrabalho) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(jornadaTrabalho)
        );
    }

    @GetMapping
    public ResponseEntity<List<JornadaTrabalho>> getJornadaList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalho> getJornadaByID(@PathVariable("idJornada") Long idJornada) {
        return ResponseEntity.ok(
                service.findById(idJornada)
        );
    }

    @PutMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalho> updateJornada(@PathVariable("idJornada") Long idJornada,
                                         @RequestBody JornadaTrabalho jornadaTrabalho) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idJornada, jornadaTrabalho)
        );
    }

    @DeleteMapping("/{idJornada}")
    public ResponseEntity deleteByID(@PathVariable("idJornada") Long idJornada) {

       service.delete(idJornada);

       return ResponseEntity.noContent().build();
    }
}
