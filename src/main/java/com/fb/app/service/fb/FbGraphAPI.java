package com.fb.app.service.fb;

import java.util.List;
import com.fb.app.service.fb.types.PhotoWithReactionsType;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;

public class FbGraphAPI {

	private final FacebookClient fbClient;
	
	private FbGraphAPI(FacebookClient fbClient) {
		this.fbClient = fbClient;
	}
	
    public static FbGraphAPI createFbDataLoader(String accessToken, Version version) {
    	
    	FacebookClient fbClient = new DefaultFacebookClient(accessToken, version);
    	
		return new FbGraphAPI(fbClient);
	}
    
    public User fetchUserInfo(String endpoint, Parameter... parameters) {
    	return fbClient.fetchObject(endpoint, User.class, parameters );
    }
    
    public List<PhotoWithReactionsType> fetchUserTaggedPhotos(String endpoint, Parameter... parameters) {
    	Connection<PhotoWithReactionsType> fetchedUserPhotos = 
    			fbClient.fetchConnection(endpoint, PhotoWithReactionsType.class, parameters);
    	
    	return fetchedUserPhotos.getData();
    }
  
}
