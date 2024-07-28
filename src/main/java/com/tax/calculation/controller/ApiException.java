package com.tax.calculation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
public class ApiException extends Exception{

//	private static final long serialVersionUID = 1L;
	public ApiException(String message) {
	 super(message);
	}
}