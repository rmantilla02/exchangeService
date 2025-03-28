package ar.com.nubi.exchange.divisas.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ErrorResponseDto {

	private HttpStatus errorCode;

	private String errorMessage;

	private LocalDateTime errorTime;

}
