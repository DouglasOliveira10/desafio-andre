package br.com.desafio.controller;

import br.com.desafio.dto.PlanetaDTO;
import br.com.desafio.controller.dto.ResponseAPI;
import br.com.desafio.exception.PlanetaException;
import br.com.desafio.service.PlanetaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/planeta")
public class PlanetaController {

    private final PlanetaService planetaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAPI criarPlanete(@RequestBody PlanetaDTO request) throws PlanetaException {
        log.info("Criando planeta: {}", request);
        var output = planetaService.criarPlaneta(request);
        log.info("Planeta criado");

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.CREATED.value())
                .data(output)
                .build();
    }
}
