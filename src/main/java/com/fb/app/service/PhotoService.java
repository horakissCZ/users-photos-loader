package com.fb.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fb.app.dto.PhotoDto;

public interface PhotoService {

	Page<PhotoDto> getAllUserTaggedPhotos(String userFbId, Pageable pageRequest);
	
}
