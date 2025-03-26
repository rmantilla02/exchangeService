package ar.com.nubi.exchange.api;

public interface ExchangeDivisaApi {
	/**
	 * 
	 * @param divisaBase
	 * @param divisaDestino
	 * @return rate de la divisa base y destino
	 */
	public Double getRate(String divisaBase, String divisaDestino);
}
