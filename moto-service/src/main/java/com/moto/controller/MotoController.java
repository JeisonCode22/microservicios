package com.moto.controller;

import com.moto.entity.Moto;
import com.moto.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos() {
        List<Moto> motos = motoService.getAll();
        if (motos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(motos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable int id) {
        Moto moto = motoService.getMotoById(id);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(moto);
        }
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
        return ResponseEntity.ok(motoService.save(moto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuario(@PathVariable int usuarioId) {
        List<Moto> motos = motoService.byUsuarioId(usuarioId);
        if (motos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(motos);
        }
    }

}
