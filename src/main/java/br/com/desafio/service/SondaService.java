package br.com.desafio.service;

import br.com.desafio.dto.SondaDTO;
import br.com.desafio.exception.SondaException;
import br.com.desafio.model.Direcao;
import br.com.desafio.model.PlanetaEntity;
import br.com.desafio.model.SondaEntity;
import br.com.desafio.repository.PlanetaRepository;
import br.com.desafio.repository.SondaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SondaService {

    private final PlanetaRepository planetaRepository;
    private final SondaRepository repository;

    public SondaDTO criarSonda(SondaDTO dto) throws SondaException {
        var modelMapper = new ModelMapper();
        var entity = modelMapper.map(dto, SondaEntity.class);

        entity.setPosicaoY(0);
        entity.setPosicaoX(0);
        entity.setDirecao(Direcao.DIREITA);
        entity.setPlanetaEntity(getPlanetaEntity(dto));

        repository.save(entity);
        return modelMapper.map(entity, SondaDTO.class);
    }

    private PlanetaEntity getPlanetaEntity(SondaDTO dto) throws SondaException {
        var planetaOptional = planetaRepository.findById(dto.getIdPlaneta());
        if (planetaOptional.isEmpty()) throw new SondaException("Planeta não encontrado com id: " + dto.getIdPlaneta());
        return planetaOptional.get();
    }
}
