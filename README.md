# exchangeService
Microservicio que realiza la conversión de una cantidad de dinero entre dos divisas.

Para obtener la tasa de cambio consume la API pública https://exchangerate.host/

## Para levantar el servicio:
- Hay que tener instalado java 17 y Maven.  Ejecutar:

      mvn clean install
      mvn spring-boot:run

- O con docker ejecutar:

      docker-compose up

## Documentación:

    http://localhost:8080/swagger-ui/index.html

## Pruebas:
- Con Postman usar el archivo exchangeService.postman_collection

- o desde una terminal ejecutar:

		curl --location --request GET 'http://localhost:8080/api/exchange-divisa' \
		--header 'Content-Type: application/json' \
		--data '{"cantidad": 12.1,
		"divisaBase": "USD",
		"divisaDestino": "ARS"}'
