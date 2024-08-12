package br.com.desafio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sonda")
public class SondaEntity {

    @Id
    @SequenceGenerator(name="sonda_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sonda_seq")
    private Long id;

    private String nome;

    private int posicaoX;

    private int posicaoY;

    @Enumerated(EnumType.STRING)
    private Direcao direcao;

    @OneToOne
    private PlanetaEntity planetaEntity;
}
