package com.fb.app.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String userFbId) {
		super("The user already exists for userFbId: " + userFbId);
	}

}
