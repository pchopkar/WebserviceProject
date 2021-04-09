package com.example.userexception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.user.UsernotfoundException;
//ControllerAdvice is used to communicate between different RestController
@ControllerAdvice
@RestController
public class CreasteUserExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) throws Exception {
		
		UserException exceptionresponse = new UserException(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionresponse,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	
	@ExceptionHandler(UsernotfoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
		
		UserException exceptionresponse = new UserException(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionresponse,HttpStatus.NOT_FOUND);
	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		UserException exceptionresponse = new UserException(new Date(), 
"Correct your parameter", ex.getBindingResult().toString());
		
		return new ResponseEntity<Object>(exceptionresponse,HttpStatus.BAD_REQUEST);
	}

	
	
	
}
