package ar.com.nubi.exchange.divisas.external.api;

public interface ExternalExchangeApi {
	/**
	 * 
	 * @param divisaBase
	 * @param divisaDestino
	 * @return rate de la divisa base y destino
	 */
	public Double getRate(String divisaBase, String divisaDestino);
}
