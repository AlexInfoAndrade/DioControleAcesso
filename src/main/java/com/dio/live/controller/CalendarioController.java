package com.dio.live.controller;

import com.dio.live.model.Calendario;
import com.dio.live.service.CalendarioService;
import com.dio.live.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendarios")
public class CalendarioController {
    private CalendarioService service;

    @Autowired
    public CalendarioController(CalendarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Calendario> createCalendario(@RequestBody Calendario Calendario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(Calendario)
        );
    }

    @GetMapping
    public ResponseEntity<List<Calendario>> getCalendarioList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idCalendario}")
    public ResponseEntity<Calendario> getCalendarioByID(@PathVariable("idCalendario") Long idCalendario) {
        return ResponseEntity.ok(
                service.findById(idCalendario)
        );
    }

    @PutMapping("/{idCalendario}")
    public ResponseEntity<Calendario> updateCalendario(@PathVariable("idCalendario") Long idCalendario,
                                         @RequestBody Calendario obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idCalendario, obj)
        );
    }

    @DeleteMapping("/{idCalendario}")
    public ResponseEntity deleteByID(@PathVariable("idCalendario") Long idCalendario) {

       service.delete(idCalendario);

       return ResponseEntity.noContent().build();
    }
}