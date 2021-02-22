package com.cg.ngo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DistributionIdNotFoundException extends RuntimeException{

	public DistributionIdNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
