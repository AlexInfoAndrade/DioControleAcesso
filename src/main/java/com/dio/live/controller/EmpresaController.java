package com.dio.live.controller;

import com.dio.live.model.Empresa;
import com.dio.live.service.EmpresaService;
import com.dio.live.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private EmpresaService service;

    @Autowired
    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa Empresa) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(Empresa)
        );
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getEmpresaList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<Empresa> getEmpresaByID(@PathVariable("idEmpresa") Long idEmpresa) {
        return ResponseEntity.ok(
                service.findById(idEmpresa)
        );
    }

    @PutMapping("/{idEmpresa}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("idEmpresa") Long idEmpresa,
                                         @RequestBody Empresa obj) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(idEmpresa, obj)
        );
    }

    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity deleteByID(@PathVariable("idEmpresa") Long idEmpresa) {

       service.delete(idEmpresa);

       return ResponseEntity.noContent().build();
    }
}
