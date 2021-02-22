package com.cg.ngo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchEmployeeException extends RuntimeException {
	public NoSuchEmployeeException() {
		super();
	}
	
	public NoSuchEmployeeException(String message) {
		super(message);
	}
}