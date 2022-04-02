package com.onlineexam.app.customExceptions;

import java.util.LinkedHashMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.onlineexam.app.dto.ServiceResponseDTO;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidMobileNumberException.class)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ServiceResponseDTO serviceResponseDTO = new ServiceResponseDTO();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		String detail = null;
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			detail = error.getDefaultMessage();
		}
		serviceResponseDTO.setServiceResponse(response);
		return new ResponseEntity<Object>(serviceResponseDTO, HttpStatus.BAD_REQUEST);
	}
}