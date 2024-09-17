package com.br.escoladeti.prova.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "viagem")
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String nome;

  @NotNull
  @Column(name = "data_saida")
  private LocalDate dataSaida;

  @Column(name = "data_chegada")
  private LocalDate dataChegada;

  private BigDecimal valor;

  @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  @JsonManagedReference
  private List<Destino> destinos;

}
