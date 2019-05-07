package com.fb.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb.app.dto.PhotoDto;
import com.fb.app.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	PhotoRepository photoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<PhotoDto> getAllUserTaggedPhotos(String userFbId) {
		return photoRepository.findByUserFbId(userFbId).stream()
				.map(photo -> modelMapper.map(photo, PhotoDto.class))
				.collect(Collectors.toList());
	}
	
}
