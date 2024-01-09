package com.amaris.task.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RepositoryException extends Exception{

	private static final long serialVersionUID = 1L;

	public RepositoryException(Throwable t) {
		super(t);
	}
	
	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable t) {
		super(message, t);
	}

}
