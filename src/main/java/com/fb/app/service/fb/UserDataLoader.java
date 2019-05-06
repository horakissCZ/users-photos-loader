package com.fb.app.service.fb;

import java.util.Set;

import org.modelmapper.ModelMapper;

import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;

public interface UserDataLoader {
	
	UserDto getUserBasicInfo(FbGraphAPI fbClient, String userFbId, ModelMapper modelMapper);
	
	Set<PhotoDto> getUserTaggedPhotosWithReactions(FbGraphAPI fbClient, String userFbId, ModelMapper modelMapper);
	
}
