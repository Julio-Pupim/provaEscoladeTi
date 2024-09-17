package com.br.escoladeti.prova.controller;

import com.br.escoladeti.prova.domain.Destino;
import com.br.escoladeti.prova.domain.Viagem;
import com.br.escoladeti.prova.service.DestinoService;
import com.br.escoladeti.prova.service.ViagemService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/viagens")
public class ViagemController {

  private final ViagemService viagemService;
  private final DestinoService destinoService;

  @PostMapping
  public ResponseEntity<Viagem> saveViagem(@RequestBody @Valid Viagem viagem) {
    Viagem saved = viagemService.save(viagem);

    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  @GetMapping
  public ResponseEntity<List<Viagem>> listarTodasViagens() {
    return ResponseEntity.ok(viagemService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Viagem> getViagem(@PathVariable("id") Long id) {
    Optional<Viagem> optionalViagem = viagemService.findById(id);

    return optionalViagem.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Viagem> atualizarViagem(@PathVariable("id") Long id,
      @RequestBody Viagem viagem) {
    Viagem updateViagem = viagemService.updateViagem(id, viagem);
    if (Objects.nonNull(updateViagem)) {
      return ResponseEntity.ok(updateViagem);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/adicionar-destino/{idViagem}/{idDestino}")
  public ResponseEntity<Viagem> adicionarDestino(@PathVariable("idViagem") Long idViagem,
      @PathVariable("idDestino") Long idDestino) {
    Destino destino = destinoService.findById(idDestino).orElse(null);
    Viagem viagem = viagemService.findById(idViagem).orElse(null);
    if (Objects.nonNull(destino) && Objects.nonNull(viagem)) {
      return ResponseEntity.ok(viagemService.adicionaDestino(viagem, destino));
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("/remover-destino/{idViagem}/{idDestino}")
  public ResponseEntity<Viagem> removerDestino(@PathVariable("idViagem") Long idViagem,
      @PathVariable("idDestino") Long idDestino) {
    Destino destino = destinoService.findById(idDestino).orElse(null);
    Viagem viagem = viagemService.findById(idViagem).orElse(null);
    if (Objects.nonNull(destino) && Objects.nonNull(viagem)) {
      return ResponseEntity.ok(viagemService.removerDestino(viagem, destino));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarViagem(@PathVariable("id") Long id) {
    this.viagemService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
