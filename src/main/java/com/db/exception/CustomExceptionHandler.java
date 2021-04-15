package com.db.exception;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler {

	private static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";
	private static final String UNPROCESSABLE_DATA_CODE = "UNPROCESSABLE_DATA";
	private static final String ARGUMENT_NOT_VALID_EXCEPTION_CODE = "ARGUMENT_NOT_VALID";

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
		List<String> details = Collections.singletonList(ex.getLocalizedMessage());

		ErrorResponse response = new ErrorResponse(INTERNAL_SERVER_ERROR_CODE, "Server Error", ErrorResponse.Severity.CRITICAL, details);
		return new ResponseEntity(response, INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<ErrorResponse> handleUnprosseasableMsgException(HttpMessageNotReadableException ex) {
		List<String> details = Collections.singletonList(ex.getLocalizedMessage());

		ErrorResponse response =  new ErrorResponse(UNPROCESSABLE_DATA_CODE, "Unprocessable input data", ErrorResponse.Severity.ERROR, details);
		return new ResponseEntity(response, UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> details.add(fieldError.getDefaultMessage()));
		//fieldError.getField() + ": " + 

		ErrorResponse response = new ErrorResponse(ARGUMENT_NOT_VALID_EXCEPTION_CODE, "Validation Failed", ErrorResponse.Severity.ERROR, details);
		return new ResponseEntity(response, BAD_REQUEST);
	}

}