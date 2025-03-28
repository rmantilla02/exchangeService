package ar.com.nubi.exchange.divisas.service;

public interface ExchangeDivisasService {
	
	/**
	 * 
	 * @param cant
	 * @param divBase
	 * @param divDestivo
	 * @return la coversion de la divisa base en la destino
	 */
	public Double obtenerConversion(Double cant, String divBase, String divDestivo);

}
