package com.dio.live.controller;

import com.dio.live.model.CategoriaUsuario;
import com.dio.live.service.CategoriaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias_usuario")
public class CategoriaUsuarioController {
    private CategoriaUsuarioService service;

    @Autowired
    public CategoriaUsuarioController(CategoriaUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaUsuario> createCategoria(@RequestBody CategoriaUsuario categoriaUsuario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(categoriaUsuario)
        );
    }

    @GetMapping
    public ResponseEntity<List<CategoriaUsuario>> getCategoriaList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaUsuario> getCategoriaByID(@PathVariable("idCategoria") Long idCategoria) {
        return ResponseEntity.ok(
                service.findById(idCategoria)
        );
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaUsuario> updateCategoria(@PathVariable("idCategoria") Long idCategoria,
                                         @RequestBody CategoriaUsuario categoriaUsuario) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idCategoria, categoriaUsuario)
        );
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity deleteByID(@PathVariable("idCategoria") Long idCategoria) {

       service.delete(idCategoria);

       return ResponseEntity.noContent().build();
    }
}
