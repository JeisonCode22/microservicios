package com.carro.controller;

import com.carro.entity.Carro;
import com.carro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros() {
        List<Carro> carros = carroService.getAll();
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(carros);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable int id) {
        Carro carro = carroService.getCarroById(id);
        if (carro == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(carro);
        }
    }

    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro) {
        return ResponseEntity.ok(carroService.save(carro));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosPorUsuario(@PathVariable int usuarioId) {
        List<Carro> carros = carroService.byUsuarioId(usuarioId);
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(carros);
        }
    }

}
