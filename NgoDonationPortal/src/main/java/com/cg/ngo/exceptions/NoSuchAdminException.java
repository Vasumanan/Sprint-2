package com.cg.ngo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchAdminException extends RuntimeException{
	public NoSuchAdminException() {
		super();
	}
	public NoSuchAdminException(String message) {
		super(message);
	}
}