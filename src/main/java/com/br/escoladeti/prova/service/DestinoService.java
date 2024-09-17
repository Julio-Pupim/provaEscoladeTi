package com.br.escoladeti.prova.service;

import com.br.escoladeti.prova.domain.Destino;
import com.br.escoladeti.prova.repository.DestinoRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DestinoService {

  private final DestinoRepository destinoRepository;

  public Destino save(Destino destinoSave) {
    return this.destinoRepository.save(destinoSave);
  }

  public Optional<Destino> findById(Long id) {
    return this.destinoRepository.findById(id);
  }

  public List<Destino> findAll() {
    return this.destinoRepository.findAll();
  }

  public Destino updateDestino(Long id, Destino destinoUpdate) {
    Destino destino = findById(id).orElse(null);
    if (Objects.nonNull(destino)) {
      BeanUtils.copyProperties(destinoUpdate, destino, "id");
      return save(destino);
    }
    return null;
  }

  public void delete(Long id) {
    this.destinoRepository.deleteById(id);
  }

}
