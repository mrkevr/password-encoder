package dev.mrkevr.passwordencoder.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.mrkevr.passwordencoder.dto.HttpErrorResponse;
import dev.mrkevr.passwordencoder.encoder.NoSuchEncoderException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(NoSuchEncoderException.class)
	public ResponseEntity<?> handleNoSuchBeanException(NoSuchEncoderException ex) {
		
		HttpErrorResponse response = new HttpErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
		
		HttpErrorResponse response = new HttpErrorResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
