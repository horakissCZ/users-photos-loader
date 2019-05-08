package com.fb.app.service.fb;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fb.app.dto.PhotoDto;
import com.fb.app.dto.UserDto;
import com.fb.app.service.fb.types.PhotoWithReactionsType;
import com.restfb.Parameter;
import com.restfb.types.User;

public class FbDataLoader implements UserDataLoader {
	
	private static final String PHOTO_ENDPOINT_API = "/photos/tagged";
	
	private static final String USER_ENDPOINT_PARAMETER = "id,name,gender,picture{url}";
	private static final String PHOTO_ENDPOINT_PARAMETER = "id,link,album{name},images";
	private static final String REACTIONS_ENDPOINT_PARAMETER = 
			"reactions.type(NONE).limit(0).summary(total_count).as(none),"
			+ "reactions.type(LIKE).limit(0).summary(total_count).as(like),"
			+ "reactions.type(LOVE).limit(0).summary(total_count).as(love),"
			+ "reactions.type(WOW).limit(0).summary(total_count).as(wow),"
			+ "reactions.type(HAHA).limit(0).summary(total_count).as(haha),"
			+ "reactions.type(SAD).limit(0).summary(total_count).as(sad),"
			+ "reactions.type(ANGRY).limit(0).summary(total_count).as(angry),"
			+ "reactions.type(THANKFUL).limit(0).summary(total_count).as(thankful),"
			+ "reactions.type(PRIDE).limit(0).summary(total_count).as(pride)";
	
    public UserDto getUserBasicInfo(FbGraphAPI fbClient, String userFbId, ModelMapper modelMapper) {
    	
    	Parameter userBasicInfo = Parameter.with("fields", USER_ENDPOINT_PARAMETER);
    	
    	User fetchedUser = fbClient.fetchUserInfo(userFbId, userBasicInfo);

    	return modelMapper.map(fetchedUser, UserDto.class);
    			
    }
    
    public Set<PhotoDto> getUserTaggedPhotosWithReactions(FbGraphAPI fbClient, String userFbId, ModelMapper modelMapper) {
    	
    	Parameter photoBasicInfoAndReactions = 
    			Parameter.with("fields", PHOTO_ENDPOINT_PARAMETER + "," + REACTIONS_ENDPOINT_PARAMETER);
    	
    	List<PhotoWithReactionsType> fetchedPhotos = 
    			fbClient.fetchUserTaggedPhotos(userFbId + PHOTO_ENDPOINT_API, photoBasicInfoAndReactions);
    	
    	return fetchedPhotos.stream()
    			.map(fetchedPhoto -> modelMapper.map(fetchedPhoto, PhotoDto.class))
    			.collect(Collectors.toSet());
    	
    }
}
