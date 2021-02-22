package com.cg.ngo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateNeedyPersonException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateNeedyPersonException()
	{
		super();
	}
	public DuplicateNeedyPersonException(String msg){
		super(msg);
	}

}
