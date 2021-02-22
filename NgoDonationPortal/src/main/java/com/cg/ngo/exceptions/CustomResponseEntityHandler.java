package com.cg.ngo.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DuplicateNeedyPersonException.class)
	public ResponseEntity<Object> handleDuplicateNeedyPersonException(DuplicateNeedyPersonException ex,WebRequest request){
		ExceptionResponse duplicateNeedyPersonException=new ExceptionResponse(LocalDate.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object> (duplicateNeedyPersonException,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchEmployeeException.class)
	public ResponseEntity<Object> handleNoSuchEmployeeException(NoSuchEmployeeException exception, WebRequest request){
		ExceptionResponse noSuchEmployeeExceptionResponse = new ExceptionResponse(LocalDate.now(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(noSuchEmployeeExceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchAdminException.class)
	public ResponseEntity<Object> handleNoSuchAdminException(NoSuchAdminException exception, WebRequest request){
		ExceptionResponse noSuchAdminExceptionResponse = new ExceptionResponse(LocalDate.now(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(noSuchAdminExceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateEmployeeException.class)
	public ResponseEntity<Object> handleDuplicateEmployeeException(DuplicateEmployeeException exception, WebRequest request){
		ExceptionResponse duplicateEmployeeExceptionResponse =  new ExceptionResponse(LocalDate.now(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(duplicateEmployeeExceptionResponse,HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(DistributionIdNotFoundException.class)
	public ResponseEntity<Object> handleDistributionIdNotFoundException(DistributionIdNotFoundException exception, WebRequest request){
		ExceptionResponse distributionIdNotFoundExceptionResponse = new ExceptionResponse(LocalDate.now(),exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(distributionIdNotFoundExceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchNeedyPersonException.class)
	public ResponseEntity<Object> handleNoSuchNeedyPersonException(NoSuchNeedyPersonException exception, WebRequest request) {
		ExceptionResponse details = new ExceptionResponse(LocalDate.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		ExceptionResponse details = new ExceptionResponse(LocalDate.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(details, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoSuchDonorException.class)
	public ResponseEntity<Object> handleNoSuchDonorException(NoSuchDonorException exception, WebRequest request) {
		ExceptionResponse details = new ExceptionResponse(LocalDate.now(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(details, HttpStatus.NOT_FOUND);
	}

}
