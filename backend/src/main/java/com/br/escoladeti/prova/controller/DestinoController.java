package com.br.escoladeti.prova.controller;

import com.br.escoladeti.prova.domain.Destino;
import com.br.escoladeti.prova.service.DestinoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/destinos")
@AllArgsConstructor
@CrossOrigin("*")
public class DestinoController {

  private final DestinoService destinoService;

  @PostMapping
  public ResponseEntity<Destino> saveDestino(@RequestBody Destino destino) {
    Destino saved = destinoService.save(destino);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping
  public ResponseEntity<List<Destino>> listarTodosOsDestinos() {
    return ResponseEntity.ok(destinoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Destino> getDestino(@PathVariable("id") Long id) {
    Optional<Destino> destino = destinoService.findById(id);

    return destino.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Destino> atualizarViagem(@PathVariable("id") Long id,
      @RequestBody @Valid Destino destino) {
    Destino updateDestino = destinoService.updateDestino(id, destino);
    if (Objects.nonNull(updateDestino)) {
      return ResponseEntity.ok(updateDestino);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarDestino(@PathVariable("id") Long id) {
    destinoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
