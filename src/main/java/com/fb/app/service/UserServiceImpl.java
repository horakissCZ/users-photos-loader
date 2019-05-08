package com.fb.app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb.app.domain.Photo;
import com.fb.app.domain.User;
import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;
import com.fb.app.repository.PhotoRepository;
import com.fb.app.repository.UserRepository;
import com.fb.app.service.fb.UserDataLoader;
import com.fb.app.service.fb.FbDataLoader;
import com.fb.app.service.fb.FbGraphAPI;
import com.restfb.Version;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Optional<UserDto> getUserDetail(String userFbId) {
		return userRepository.findByUserFbId(userFbId)
				.map(user -> modelMapper.map(user, UserDto.class));
	}

	@Override
	public void createUser(UserDto userDto) {
		
		FbGraphAPI fbClient = 
				FbGraphAPI.createFbDataLoader(userDto.getAccessToken(), Version.VERSION_3_2);
	
		UserDataLoader dataLoader = new FbDataLoader();
		UserDto newUser = dataLoader.getUserBasicInfo(fbClient, userDto.getUserFbId(), modelMapper);
		
		if(newUser != null) {
			Set<PhotoDto> userTaggedPhotos = 
					dataLoader.getUserTaggedPhotosWithReactions(fbClient, userDto.getUserFbId(), modelMapper);
			newUser.setPhotos(userTaggedPhotos);
			userRepository.save(modelMapper.map(newUser, User.class));
			
			List<Photo> photosWithoutUser = photoRepository.findPhotosWithoutUser();
			photoRepository.deleteAll(photosWithoutUser);
		}
		
	}

	@Override
	public Optional<UserDto> removeUser(String userFbId) {
		
		return userRepository.findByUserFbId(userFbId)
				.map(user -> {
					userRepository.delete(user);
					return Optional.of(modelMapper.map(user, UserDto.class));
					})
				.orElse(Optional.empty());
	}

}
