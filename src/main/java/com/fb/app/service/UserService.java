package com.fb.app.service;

import java.util.List;

import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;

public interface UserService {
	
	UserDto getUserDetail(String userFbId);
	
	List<PhotoDto> getUserPhotos(String userFbId);

	void createUser(UserDto accessToken);

	void removeUser(String userFbId);
	
}
