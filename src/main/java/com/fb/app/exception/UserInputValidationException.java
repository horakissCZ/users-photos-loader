package com.fb.app.exception;

public class UserInputValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserInputValidationException(String msg) {
		super(msg);
	}
}
