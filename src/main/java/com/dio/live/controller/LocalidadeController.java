package com.dio.live.controller;

import com.dio.live.model.Localidade;
import com.dio.live.service.LocalidadeService;
import com.dio.live.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
public class LocalidadeController {
    private LocalidadeService service;

    @Autowired
    public LocalidadeController(LocalidadeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Localidade> createLocalidade(@RequestBody Localidade Localidade) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(Localidade)
        );
    }

    @GetMapping
    public ResponseEntity<List<Localidade>> getLocalidadeList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idLocalidade}")
    public ResponseEntity<Localidade> getLocalidadeByID(@PathVariable("idLocalidade") Long idLocalidade) {
        return ResponseEntity.ok(
                service.findById(idLocalidade)
        );
    }

    @PutMapping("/{idLocalidade}")
    public ResponseEntity<Localidade> updateLocalidade(@PathVariable("idLocalidade") Long idLocalidade,
                                         @RequestBody Localidade obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idLocalidade, obj)
        );
    }

    @DeleteMapping("/{idLocalidade}")
    public ResponseEntity deleteByID(@PathVariable("idLocalidade") Long idLocalidade) {

       service.delete(idLocalidade);

       return ResponseEntity.noContent().build();
    }
}
