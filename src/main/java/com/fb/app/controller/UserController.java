package com.fb.app.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fb.app.dto.UserDto;
import com.fb.app.exception.UserAlreadyExistsException;
import com.fb.app.exception.UserInputValidationException;
import com.fb.app.exception.UserNotFoundException;
import com.fb.app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	Validator validator;
	
	@GetMapping("/users/{user_fb_id}")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable(name="user_fb_id") String userFbId) {
		
		return userService.getUserDetail(userFbId)
				.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElseThrow(() -> new UserNotFoundException(userFbId));
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody UserDto user, UriComponentsBuilder ucBuilder) {
		
		Set<ConstraintViolation<UserDto>> failures = validator.validate(user);
	    if (!failures.isEmpty())
	        throw new UserInputValidationException(failures.stream().findFirst().get().getMessage());
	    
		if(userService.getUserDetail(user.getUserFbId()).isPresent())
			throw new UserAlreadyExistsException(user.getUserFbId());
		
		userService.createUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{user_fb_id}").buildAndExpand(user.getUserFbId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{user_fb_id}")
	public ResponseEntity<UserDto> removeUser(@PathVariable(name="user_fb_id") String userFbId) {
		
		return userService.removeUser(userFbId)
				.map(user -> new ResponseEntity<>(user, HttpStatus.NO_CONTENT))
				.orElseThrow(() -> new UserNotFoundException(userFbId));
	}
	
}
