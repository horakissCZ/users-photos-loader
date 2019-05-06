package com.fb.app.service.fb.modelmapper;

import org.modelmapper.PropertyMap;

import com.fb.app.dto.UserDto;
import com.restfb.types.User;

public class UserFbToDtoMap extends PropertyMap<User, UserDto>{

	@Override
	protected void configure() {
		map(source.getId(), destination.getUserFbId());
		map(source.getName(), destination.getName());
		map(source.getGender(), destination.getGender());
		map(source.getPicture().getUrl(), destination.getProfilePictureUrl());
	}

}
