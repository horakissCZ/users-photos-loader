package com.fb.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fb.app.domain.Photo;
import com.fb.app.dto.PhotoDto;
import com.fb.app.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	PhotoRepository photoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Page<PhotoDto> getAllUserTaggedPhotos(String userFbId, Pageable pageRequest) {
		
		Page<Photo> photos = photoRepository.findByUserFbId(userFbId, pageRequest);
		List<PhotoDto> dtoPhotos = photos.stream()
				.map(photo -> modelMapper.map(photo, PhotoDto.class))
				.collect(Collectors.toList());
		
		return new PageImpl<>(dtoPhotos, pageRequest, photos.getTotalElements());
	}
	
}
