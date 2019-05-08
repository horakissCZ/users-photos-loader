package com.fb.app.service;

import java.util.Optional;

import com.fb.app.dto.UserDto;
import com.fb.app.exception.UserNotFoundException;

public interface UserService {
	
	Optional<UserDto> getUserDetail(String userFbId);

	void createUser(UserDto accessToken);

	Optional<UserDto> removeUser(String userFbId) throws UserNotFoundException;
	
}
