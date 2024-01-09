package com.amaris.task.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ModelProcessingException extends Exception{

	private static final long serialVersionUID = 1L;

	public ModelProcessingException(Throwable t) {
		super(t);
	}
	
	public ModelProcessingException(String message) {
		super(message);
	}

	public ModelProcessingException(String message, Throwable t) {
		super(message, t);
	}

}
