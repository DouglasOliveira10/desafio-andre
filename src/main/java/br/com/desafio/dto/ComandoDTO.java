package br.com.desafio.dto;

import lombok.Data;

@Data
public class ComandoDTO {
    private Long idSonda;
    private Comando comando;

    public enum Comando {
        M("Andar para frente na direção que esta 1 posição"),
        L("Virar a sonda para a esquerda (90 graus)"),
        R("Virar a sonda para a direita (90 graus)");

        Comando(String s) {    }
    }

}
