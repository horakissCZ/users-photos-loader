package com.fb.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;
import com.fb.app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users/{user_fb_id}")
	public UserDto getUserDetails(@PathVariable(name="user_fb_id") String userFbId) {
		return userService.getUserDetail(userFbId);
	}
	
	@GetMapping("/users/{user_fb_id}/photos")
	public List<PhotoDto> getUserPhotos(@PathVariable(name="user_fb_id") String userFbId) {
		return userService.getUserPhotos(userFbId);
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody UserDto user) {
		userService.createUser(user);
	}
	
	@DeleteMapping("/users/{user_fb_id}")
	public void removeUser(@PathVariable(name="user_fb_id") String userFbId) {
		userService.removeUser(userFbId);
	}
	
}
