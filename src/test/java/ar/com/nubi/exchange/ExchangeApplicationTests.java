package ar.com.nubi.exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ar.com.nubi.exchange.api.ExchangeDivisaApi;
import ar.com.nubi.exchange.api.impl.ExchangeDivisaApiImpl;

//@SpringBootTest
class ExchangeApplicationTests {

	@Test
	void contextLoads() {
	}

	private final ExchangeDivisaApi exchangeDivisaApi = new ExchangeDivisaApiImpl();

	@Test
	void testGetRateOk() {
		Double result = exchangeDivisaApi.getRate("USD", "GBP");

		System.out.println("result: " + result);
		Assert.notNull(result, "resultado inválido");
	}

	@Test
	void testGetRateError() {
		Exception exception = assertThrows(Exception.class, () -> {
			exchangeDivisaApi.getRate("UD", "GBP");
		});

		assertEquals(Exception.class, exception.getClass());

	}

}
