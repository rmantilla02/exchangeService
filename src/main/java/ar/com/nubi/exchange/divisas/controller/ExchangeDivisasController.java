package ar.com.nubi.exchange.divisas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.nubi.exchange.divisas.dto.RequestExchangeDivisaDto;
import ar.com.nubi.exchange.divisas.service.ExchangeDivisasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class ExchangeDivisasController {

	private final Logger log = LoggerFactory.getLogger(ExchangeDivisasController.class);
	private ExchangeDivisasService exchangeDivisasService;

	public ExchangeDivisasController(ExchangeDivisasService exchangeDivisasService) {
		this.exchangeDivisasService = exchangeDivisasService;
	}

	/**
	 * 
	 * @param request
	 * @return la conversión de la cantidad de la divisaBase en la divisaDestivo
	 */
	@GetMapping("/exchange-divisa")
	public ResponseEntity<String> exchangeDivisa(@Valid @RequestBody RequestExchangeDivisaDto request) {
		log.debug("request to exchangeDivisa: {}", request);
		Double result = null;
		result = exchangeDivisasService.obtenerConversion(request.getCantidad(), request.getDivisaBase(),
				request.getDivisaDestino());
		return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(result));

	}

}
