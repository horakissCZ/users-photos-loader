package com.fb.app.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userFbId) {
		super("could not find a user with userFbId: " + userFbId);
	}

}
