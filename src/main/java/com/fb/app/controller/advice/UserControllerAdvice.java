package com.fb.app.controller.advice;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fb.app.exception.UserAlreadyExistsException;
import com.fb.app.exception.UserInputValidationException;
import com.fb.app.exception.UserNotFoundException;
import com.restfb.exception.FacebookGraphException;

@ControllerAdvice
public class UserControllerAdvice {

	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors userNotFoundHandler(UserNotFoundException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors userFbIdAlreadyExistsHandler(UserAlreadyExistsException ex) {
		return new VndErrors("error", ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(FacebookGraphException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	VndErrors fbRestApiHandler(FacebookGraphException ex) {
		return new VndErrors("error", ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UserInputValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	VndErrors wrongUserInputHandler(Exception ex) {
		return new VndErrors("error", ex.getMessage());
	}
}
