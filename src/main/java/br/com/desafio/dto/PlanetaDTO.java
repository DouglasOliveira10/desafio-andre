package br.com.desafio.dto;

import lombok.Data;

@Data
public class PlanetaDTO {
    private Long id;
    private String nome;
    private int maxX;
    private int maxY;
    private boolean permiteBordaInfinita;
    private boolean permitesobreposiçao;
}
