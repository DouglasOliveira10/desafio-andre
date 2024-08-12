package br.com.desafio.controller;

import br.com.desafio.dto.ComandoDTO;
import br.com.desafio.exception.SondaException;
import br.com.desafio.service.SondaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.dto.SondaDTO;
import br.com.desafio.controller.dto.ResponseAPI;
import br.com.desafio.exception.PlanetaException;
import br.com.desafio.service.TrafegoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sonda")
public class SondaController {

	private final SondaService sondaService;
	private final TrafegoService trafegoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseAPI criarSonda(@RequestBody SondaDTO request) throws SondaException {
		log.info("Criando nova Sonda: {}", request);
		var output = sondaService.criarSonda(request);
		log.info("Sonda Criada com sucesso: {}", output);

		return ResponseAPI.builder()
				.httpStatusCode(HttpStatus.CREATED.value())
				.data(output)
				.build();
	}
	
	@PostMapping("/movimento")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseAPI moverSonda(@RequestBody ComandoDTO request) throws SondaException {
		log.info("Movendo Sonda: {}", request);
		var output = trafegoService.moverSonda(request);
		log.info("Sonda Movida com sucesso: {}", output);

		return ResponseAPI.builder()
				.httpStatusCode(HttpStatus.CREATED.value())
				.data(output)
				.build();
	}
}
