package com.dio.live.controller;

import com.dio.live.model.Usuario;
import com.dio.live.service.UsuarioService;
import com.dio.live.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
    private UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario Usuario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(Usuario)
        );
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarioList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> getUsuarioByID(@PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok(
                service.findById(idUsuario)
        );
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("idUsuario") Long idUsuario,
                                         @RequestBody Usuario obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idUsuario, obj)
        );
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity deleteByID(@PathVariable("idUsuario") Long idUsuario) {

       service.delete(idUsuario);

       return ResponseEntity.noContent().build();
    }
}
