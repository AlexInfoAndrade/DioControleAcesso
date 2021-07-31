package com.dio.live.controller;

import com.dio.live.dto.BancoHorasDTO;
import com.dio.live.model.BancoHoras;
import com.dio.live.service.BancoHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco_horas")
public class BancoHorasController {
    private BancoHorasService service;

    @Autowired
    public BancoHorasController(BancoHorasService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BancoHoras> createBanco(@RequestBody BancoHorasDTO bancoHorasDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.save(bancoHorasDTO.toModel())
        );
    }

    @GetMapping
    public ResponseEntity<List<BancoHoras>> getBancoList() {
        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{idBanco}/{idMovimentacao}/{idUsuario}")
    public ResponseEntity<BancoHoras> getBancoByID(
            @PathVariable("idBanco") Long idBanco,
            @PathVariable("idMovimentacao") Long idMovimentacao,
            @PathVariable("idUsuario") Long idUsuario) {
        
        return ResponseEntity.ok(
                service.findById(idBanco, idMovimentacao, idUsuario)
        );
    }

    @PutMapping("/")
    public ResponseEntity<BancoHoras> updateBanco(@RequestBody BancoHorasDTO bancoHorasDTO) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                service.update(
                        bancoHorasDTO.getIdBancoHoras(),
                        bancoHorasDTO.getIdMovimentacao(),
                        bancoHorasDTO.getIdUsuario(),
                        bancoHorasDTO.toModel()
                )
        );
    }

    @DeleteMapping("/{idBanco}/{idMovimentacao}/{idUsuario}")
    public ResponseEntity deleteByID(
            @PathVariable("idBanco") Long idBanco,
            @PathVariable("idMovimentacao") Long idMovimentacao,
            @PathVariable("idUsuario") Long idUsuario) {

       service.delete(idBanco, idMovimentacao, idUsuario);

       return ResponseEntity.noContent().build();
    }
}
