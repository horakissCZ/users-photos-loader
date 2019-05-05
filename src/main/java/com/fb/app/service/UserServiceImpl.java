package com.fb.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb.app.domain.User;
import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;
import com.fb.app.repository.PhotoRepository;
import com.fb.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto getUserDetail(String userFbId) {
		return modelMapper.map(userRepository.findByUserFbId(userFbId), UserDto.class);
	}

	@Override
	public List<PhotoDto> getUserPhotos(String userFbId) {
		return photoRepository.findByUserFbId(userFbId).stream()
				.map(photo -> modelMapper.map(photo, PhotoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void createUser(UserDto userDto) {
		userRepository.save(modelMapper.map(userDto, User.class));
	}

	@Override
	public void removeUser(String userFbId) {
		User foundedUser = userRepository.findByUserFbId(userFbId);
		
		if(foundedUser != null)
			userRepository.delete(foundedUser);
	}

}
