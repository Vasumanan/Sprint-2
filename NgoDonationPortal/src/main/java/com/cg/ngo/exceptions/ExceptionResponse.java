package com.cg.ngo.exceptions;

import java.time.LocalDate;
public class ExceptionResponse {
	private LocalDate date;
	private String message;
	private String details;

	public ExceptionResponse(LocalDate date,String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}
	public LocalDate getdate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
