package br.com.desafio.dto;

import br.com.desafio.model.Direcao;
import lombok.Data;

@Data
public class SondaDTO {
    private Long id;
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private Direcao direcao;
    private Long idPlaneta;
}
