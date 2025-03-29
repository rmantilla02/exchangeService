package ar.com.nubi.exchange.divisas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.com.nubi.exchange.divisas.external.api.ExternalExchangeApi;
import ar.com.nubi.exchange.divisas.service.ExchangeDivisasService;

@Service
public class ExchangeDivisasServiceImpl implements ExchangeDivisasService {

	private final Logger log = LoggerFactory.getLogger(ExchangeDivisasServiceImpl.class);

	private ExternalExchangeApi externalExchangeApi;

	public ExchangeDivisasServiceImpl(ExternalExchangeApi externalExchangeApi) {
		this.externalExchangeApi = externalExchangeApi;
	}

	/**
	 * 
	 * @param cant
	 * @param divBase
	 * @param divDestivo
	 * @return la coversion de la divisa base en la destino
	 */
	@Override
	public Double obtenerConversion(Double cant, String divBase, String divDestivo) {
		log.debug("obteniendo conversión: {}", cant, divBase, divDestivo);
		Double ratio = externalExchangeApi.getRate(divBase, divDestivo);
		return cant * ratio;
	}

}
