package com.cg.ngo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEmployeeException extends RuntimeException{
	public DuplicateEmployeeException(String errMsg){
		super(errMsg);
	}	
	public DuplicateEmployeeException(){
		
	}
}
