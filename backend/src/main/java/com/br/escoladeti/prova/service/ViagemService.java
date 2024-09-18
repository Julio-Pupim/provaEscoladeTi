package com.br.escoladeti.prova.service;

import com.br.escoladeti.prova.domain.Destino;
import com.br.escoladeti.prova.domain.Viagem;
import com.br.escoladeti.prova.repository.DestinoRepository;
import com.br.escoladeti.prova.repository.ViagemRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ViagemService {

  private final ViagemRepository viagemRepository;
  private final DestinoRepository destinoRepository;

  public Viagem save(Viagem viagemSave) {
    return this.viagemRepository.save(viagemSave);
  }

  public List<Viagem> findAll() {
    return this.viagemRepository.findAll();
  }

  public Optional<Viagem> findById(Long id) {
    return this.viagemRepository.findById(id);
  }

  public Viagem updateViagem(Long id, Viagem updateViagem) {
    Viagem viagem = findById(id).orElse(null);

    if (Objects.nonNull(viagem)) {
      BeanUtils.copyProperties(updateViagem, viagem, "id");
      return save(viagem);
    }
    return null;
  }

  public void delete(Long id) {
    this.viagemRepository.deleteById(id);
  }

  public Viagem removerDestino(Viagem viagem, Long idDestino) {
    destinoRepository.findById(idDestino).ifPresent(
        destino -> {
          viagem.getDestinos().remove(destino);
          destinoRepository.delete(destino);
        });
    return viagem;
  }

  public Viagem adicionaDestino(Viagem viagem, Destino destino) {
    destino.setViagem(viagem);
    destinoRepository.save(destino);
    viagem.getDestinos().add(destino);
    return viagem;
  }
}
