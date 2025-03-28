package ar.com.nubi.exchange.divisas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "RequestExchangeDivisaDto",
		description = "Request para invocar al servicio exchange-divisa")
public class RequestExchangeDivisaDto {

	@Schema(description = "Cantidad a convertir", example = "14")
	@Positive(message = "La cantidad no puede ser un valor negativo")
	Double cantidad;

	@Schema(description = "Divisa base", example = "USD")
	@NotEmpty(message = "divisaBase no puede ser null o empty")
	@Size(min = 3, max = 3, message = "La longitud de la divisa base es 3")
	String divisaBase;

	@Schema(description = "Divisa destino", example = "ARS")
	@NotEmpty(message = "divisaDestino no puede ser null o empty")
	@Size(min = 3, max = 3, message = "La longitud de la divisa base es 3")
	String divisaDestino;

}
