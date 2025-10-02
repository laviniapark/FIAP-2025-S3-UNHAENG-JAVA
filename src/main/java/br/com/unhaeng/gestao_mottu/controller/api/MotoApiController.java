package br.com.unhaeng.gestao_mottu.controller.api;

import br.com.unhaeng.gestao_mottu.model.Moto;
import br.com.unhaeng.gestao_mottu.service.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoApiController {

    private final MotoService service;

    @GetMapping
    public ResponseEntity<List<Moto>> listAll() {
        return ResponseEntity.ok(service.getAllMotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMotoById(id));
    }

    @PostMapping
    public ResponseEntity<Moto> create(@RequestBody @Valid Moto moto) {
        Moto saved = service.create(moto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/motos/" + saved.getMotoId())
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moto> update(@PathVariable Long id, @RequestBody Moto moto) {
        Moto updated = service.update(id, moto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        return ResponseEntity.noContent().build();

    }
}
