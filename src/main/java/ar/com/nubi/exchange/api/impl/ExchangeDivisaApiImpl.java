package ar.com.nubi.exchange.api.impl;

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

import ar.com.nubi.exchange.api.ExchangeDivisaApi;


public class ExchangeDivisaApiImpl implements ExchangeDivisaApi {

	public static final String ACCESS_KEY = "fe1d61ef46d74b03c1f9ef04738ad5b9";
	public static final String BASE_URL = "http://api.exchangerate.host/";
	public static final String ENDPOINT = "live";

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

			// Parsed JSON Objects are accessed according to the JSON resonse's hierarchy,
			// output strings are built
			result = exchangeRates.getJSONObject("quotes").getDouble(divisaBase + divisaDestino);

			response.close();
			return result;
		} catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return null;
	}

}
