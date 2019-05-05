package com.fb.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public UserDto getUserDetail(String userFbId) {
		return null;
	}

	@Override
	public List<PhotoDto> getUserPhotos(String userFbId) {
		return null;
	}

	@Override
	public void createUser(UserDto userDto) {
		
	}

	@Override
	public void removeUser(String userFbId) {
		
	}

}
