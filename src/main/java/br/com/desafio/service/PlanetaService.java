package br.com.desafio.service;

import br.com.desafio.dto.PlanetaDTO;
import br.com.desafio.exception.PlanetaException;
import br.com.desafio.model.PlanetaEntity;
import br.com.desafio.repository.PlanetaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanetaService {

    private final PlanetaRepository planetaRepository;

    public PlanetaDTO criarPlaneta(PlanetaDTO dto) {
        var modelMapper = new ModelMapper();
        var entity = modelMapper.map(dto, PlanetaEntity.class);

        planetaRepository.save(entity);
        return modelMapper.map(entity, PlanetaDTO.class);
    }

    public PlanetaDTO buscarPlanetaPorId(Long idPlaneta) throws PlanetaException {
        var planetaOptional = planetaRepository.findById(idPlaneta);

        if (planetaOptional.isPresent()) {
            var modelMapper = new ModelMapper();
            return modelMapper.map(planetaOptional.get(), PlanetaDTO.class);
        }

        throw new PlanetaException("Planeta inexistente");
    }

    public List<PlanetaDTO> listarPlanetas() {
        var entities = planetaRepository.findAll();
        var modelMapper = new ModelMapper();
        return entities.stream().map(e -> modelMapper.map(e, PlanetaDTO.class)).toList();
    }
}
