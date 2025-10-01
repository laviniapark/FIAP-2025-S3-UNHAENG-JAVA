package br.com.unhaeng.gestao_mottu.controller.api;

import br.com.unhaeng.gestao_mottu.model.Filial;
import br.com.unhaeng.gestao_mottu.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiais")
public class FilialApiController {

    private FilialService service;

    @GetMapping
    public ResponseEntity<List<Filial>> listAll() {
        return ResponseEntity.ok(service.getAllFiliais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFilialById(id));
    }

    @PostMapping
    public ResponseEntity<Filial> create(@RequestBody @Valid Filial filial) {
        Filial saved = service.create(filial);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/filiais/" + saved.getFilialId())
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filial> update(@PathVariable Long id, @RequestBody Filial filial) {
        Filial updated = service.update(id, filial);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        return ResponseEntity.noContent().build();

    }
}
