package com.usuario.controller;

import com.usuario.entity.Usuario;
import com.usuario.models.Carro;
import com.usuario.models.Moto;
import com.usuario.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable int usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        }
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable int usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        }
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable int usuarioId, @RequestBody Carro carro) {
        Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarCarro(@PathVariable int usuarioId, @RequestBody Moto moto) {
        Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable int usuarioId) {
        Map<String, Object> resultado = usuarioService.getUsuariosAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }
}
