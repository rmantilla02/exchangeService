package ar.com.nubi.exchange.divisas.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.nubi.exchange.divisas.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResouceNotFoundException(ResouceNotFoundException exception) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.NOT_FOUND, exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException exception) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.BAD_REQUEST, exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExternalApiException.class)
	public ResponseEntity<ErrorResponseDto> handleExternalApiException(ExternalApiException exception) {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.BAD_GATEWAY, exception.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR,
				exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
