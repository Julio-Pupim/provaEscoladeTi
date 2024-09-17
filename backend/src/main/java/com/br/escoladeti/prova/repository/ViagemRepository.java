package com.br.escoladeti.prova.repository;

import com.br.escoladeti.prova.domain.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

}
