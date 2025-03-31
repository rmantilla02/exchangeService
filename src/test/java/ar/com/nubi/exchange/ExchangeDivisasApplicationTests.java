package ar.com.nubi.exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ar.com.nubi.exchange.divisas.ExchangeDivisasApplication;
import ar.com.nubi.exchange.divisas.exception.ExternalApiException;
import ar.com.nubi.exchange.divisas.external.api.ExternalExchangeApi;

@SpringBootTest(classes = ExchangeDivisasApplication.class)
class ExchangeDivisasApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ExternalExchangeApi exchangeDivisaApi;

	@Test
	void testGetRateOk() {
		Double result = exchangeDivisaApi.getRate("USD", "GBP");

		System.out.println("result: " + result);
		Assert.notNull(result, "resultado inválido");
	}

	@Test
	void testGetRateError() {
		ExternalApiException exception = assertThrows(ExternalApiException.class, () -> {
			exchangeDivisaApi.getRate("UD", "GBP");
		});

		assertEquals(ExternalApiException.class, exception.getClass());
	}

}
