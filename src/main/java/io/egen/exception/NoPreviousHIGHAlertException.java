package io.egen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class NoPreviousHIGHAlertException extends RuntimeException{

	public NoPreviousHIGHAlertException(String message) {
		super(message);
	}
}
