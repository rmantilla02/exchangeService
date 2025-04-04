package ar.com.nubi.exchange.divisas.external.api;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.nubi.exchange.divisas.exception.BadRequestException;
import ar.com.nubi.exchange.divisas.exception.ExternalApiException;
import ar.com.nubi.exchange.divisas.exception.ResouceNotFoundException;

@Component
public class ExternalExchangeApiImpl implements ExternalExchangeApi {

	@Value("${exchange.api.access.key}")
	private String ACCESS_KEY;

	@Value("${exchange.api.base.url}")
	private String BASE_URL;

	@Value("${exchange.api.endpoint}")
	private String ENDPOINT;

	static CloseableHttpClient httpClient = HttpClients.createDefault();

	public Double getRate(String divisaBase, String divisaDestino) {
		Double result = null;

		HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY + "&source=" + divisaBase
				+ "&currencies=" + divisaDestino);
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();

			// the following line converts the JSON Response to an equivalent Java Object
			JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

			result = exchangeRates.getJSONObject("quotes").getDouble(divisaBase + divisaDestino);
			response.close();

			return result;
		} catch (ClientProtocolException e) {
			throw new BadRequestException("Error en la solicitud al servicio externo: " + e.getMessage(), e);
		} catch (IOException e) {
			throw new ResouceNotFoundException("Error de comunicación con el servicio externo: " + e.getMessage(), e);
		} catch (JSONException e) {
			throw new ExternalApiException("Error al procesar los datos. " + e.getMessage(), e);
		}
	}

}
