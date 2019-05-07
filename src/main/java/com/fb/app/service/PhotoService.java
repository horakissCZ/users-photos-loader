package com.fb.app.service;

import java.util.List;

import com.fb.app.dto.PhotoDto;

public interface PhotoService {

	List<PhotoDto> getAllUserTaggedPhotos(String userFbId);
}
