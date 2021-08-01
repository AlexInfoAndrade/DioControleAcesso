package com.dio.live.controller;

import com.dio.live.model.TipoData;
import com.dio.live.service.TipoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tipos")
public class TipoDataController {
    private TipoDataService service;

    @Autowired
    public TipoDataController(TipoDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoData> createTipo(@RequestBody TipoData tipoData) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(tipoData)
        );
    }

    @GetMapping
    public ResponseEntity<List<TipoData>> getTipoList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idTipo}")
    public ResponseEntity<TipoData> getTipoByID(@PathVariable("idTipo") Long idTipo) {
        return ResponseEntity.ok(
                service.findById(idTipo)
        );
    }

    @PutMapping("/{idTipo}")
    public ResponseEntity<TipoData> updateTipo(@PathVariable("idTipo") Long idTipo,
                                         @RequestBody TipoData tipoData) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idTipo, tipoData)
        );
    }

    @DeleteMapping("/{idTipo}")
    public ResponseEntity deleteByID(@PathVariable("idTipo") Long idTipo) {

       service.delete(idTipo);

       return ResponseEntity.noContent().build();
    }
}
