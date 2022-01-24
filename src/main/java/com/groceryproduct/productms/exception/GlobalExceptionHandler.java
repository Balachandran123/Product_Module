package com.groceryproduct.productms.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.groceryproduct.productms.response.entity.ErrorInfo;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleException(Exception ex, HttpServletRequest req) {

		return new ResponseEntity<>(new ErrorInfo(HttpStatus.NOT_FOUND, 404, ex.getMessage(), req.getRequestURI()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> handleValidException(MethodArgumentNotValidException mvx, HttpServletRequest req) {
		Map<String, String> errors = new HashMap<>();

		mvx.getBindingResult().getAllErrors().forEach((err) -> {
			String field = ((FieldError) err).getField();
			String errMsg = err.getDefaultMessage();
			errors.put(field, errMsg);
		});

		return new ResponseEntity<>(new ErrorInfo(HttpStatus.BAD_REQUEST, 400, errors.toString(), req.getRequestURI()),
				HttpStatus.BAD_REQUEST);
	}
}
