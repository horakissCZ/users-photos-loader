package com.fb.app.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	public UserDto getUserDetail(String userFbId) {
		User foundedUser = userRepository.findByUserFbId(userFbId);
		
		return foundedUser != null ? 
				modelMapper.map(foundedUser, UserDto.class) : null;
	}

	@Override
	public List<PhotoDto> getUserPhotos(String userFbId) {
		return photoRepository.findByUserFbId(userFbId).stream()
				.map(photo -> modelMapper.map(photo, PhotoDto.class))
				.collect(Collectors.toList());
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
			System.out.println("Je to: " + photosWithoutUser.size());
			photoRepository.deleteAll(photosWithoutUser);
		}
		
	}

	@Override
	public void removeUser(String userFbId) {
		User foundedUser = userRepository.findByUserFbId(userFbId);
		
		if(foundedUser != null)
			userRepository.delete(foundedUser);
	}

}
