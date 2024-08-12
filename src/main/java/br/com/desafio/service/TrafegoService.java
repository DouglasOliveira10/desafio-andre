package br.com.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.desafio.dto.ComandoDTO;
import br.com.desafio.dto.PlanetaDTO;
import br.com.desafio.exception.SondaException;
import br.com.desafio.model.Direcao;
import br.com.desafio.model.SondaEntity;
import br.com.desafio.repository.SondaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.desafio.dto.SondaDTO;
import br.com.desafio.model.PlanetaEntity;
import br.com.desafio.repository.PlanetaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrafegoService {
	
	private final PlanetaRepository planetaRepository;
	private final SondaRepository sondaRepository;

	public SondaDTO moverSonda(ComandoDTO comandoDTO) throws SondaException {
		var sondaOptional = sondaRepository.findById(comandoDTO.getIdSonda());
		if(sondaOptional.isEmpty()) throw new SondaException("Sonda não encontrada!");
		var sondaEntity = sondaOptional.get();
		var planetaEntity = sondaEntity.getPlanetaEntity();

		if(comandoDTO.getComando() == ComandoDTO.Comando.M) {
			if(sondaEntity.getDirecao() == Direcao.CIMA) {
				var posicaoY = sondaEntity.getPosicaoY();
				posicaoY -= 1;
				posicaoY = ajustarPosicaoY(posicaoY, planetaEntity);
				sondaEntity.setPosicaoY(posicaoY);
			}

			else if(sondaEntity.getDirecao() == Direcao.BAIXO) {
				var posicaoY = sondaEntity.getPosicaoY();
				posicaoY += 1;
				posicaoY = ajustarPosicaoY(posicaoY, planetaEntity);
				sondaEntity.setPosicaoY(posicaoY);
			}

			else if(sondaEntity.getDirecao() == Direcao.DIREITA) {
				var posicaoX = sondaEntity.getPosicaoX();
				posicaoX += 1;
				posicaoX = ajustarPosicaoX(posicaoX, planetaEntity);
				sondaEntity.setPosicaoX(posicaoX);
			}

			else if(sondaEntity.getDirecao() == Direcao.ESQUERDA) {
				var posicaoX = sondaEntity.getPosicaoX();
				posicaoX -= 1;
				posicaoX = ajustarPosicaoX(posicaoX, planetaEntity);
				sondaEntity.setPosicaoX(posicaoX);
			}
		}

		else if(comandoDTO.getComando() == ComandoDTO.Comando.L) {
			if(sondaEntity.getDirecao() == Direcao.CIMA) {
				sondaEntity.setDirecao(Direcao.ESQUERDA);
			}

			else if(sondaEntity.getDirecao() == Direcao.ESQUERDA) {
				sondaEntity.setDirecao(Direcao.BAIXO);
			}

			else if(sondaEntity.getDirecao() == Direcao.BAIXO) {
				sondaEntity.setDirecao(Direcao.DIREITA);
			}

			else if(sondaEntity.getDirecao() == Direcao.DIREITA) {
				sondaEntity.setDirecao(Direcao.CIMA);
			}
		}

		else if(comandoDTO.getComando() == ComandoDTO.Comando.R) {
			if(sondaEntity.getDirecao() == Direcao.CIMA) {
				sondaEntity.setDirecao(Direcao.DIREITA);
			}

			else if(sondaEntity.getDirecao() == Direcao.DIREITA) {
				sondaEntity.setDirecao(Direcao.BAIXO);
			}

			else if(sondaEntity.getDirecao() == Direcao.BAIXO) {
				sondaEntity.setDirecao(Direcao.ESQUERDA);
			}

			else if(sondaEntity.getDirecao() == Direcao.ESQUERDA) {
				sondaEntity.setDirecao(Direcao.CIMA);
			}
		}

		var entity = sondaRepository.save(sondaEntity);

		var modelMapper = new ModelMapper();
		return modelMapper.map(entity, SondaDTO.class);
	}

	private static int ajustarPosicaoY(int posicaoY, PlanetaEntity planetaEntity) throws SondaException {
		if((posicaoY > planetaEntity.getMaxY() - 1 ||  posicaoY < 0 ) && !planetaEntity.isPermiteBordaInfinita()) throw new SondaException("Movimento excedeu o tamanho do planeta!");
		if(posicaoY > planetaEntity.getMaxY() - 1) posicaoY = 0;
		if(posicaoY < 0) posicaoY = planetaEntity.getMaxY() - 1;
		return posicaoY;
	}

	private static int ajustarPosicaoX(int posicaoX, PlanetaEntity planetaEntity) throws SondaException {
		if((posicaoX > planetaEntity.getMaxX() - 1 ||  posicaoX < 0 ) && !planetaEntity.isPermiteBordaInfinita()) throw new SondaException("Movimento excedeu o tamanho do planeta!");
		if(posicaoX > planetaEntity.getMaxX() - 1) posicaoX = 0;
		if(posicaoX < 0) posicaoX = planetaEntity.getMaxX() - 1;
		return posicaoX;
	}

}
