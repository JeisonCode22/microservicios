package com.usuario.feign;

import com.usuario.models.Carro;
import com.usuario.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-carro", url = "http://localhost:8002")
public interface CarroFeignClient {
    @PostMapping("/carro")
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/carro/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable int usuarioId);
}
