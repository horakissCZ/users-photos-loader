package com.fb.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fb.app.service.fb.modelmapper.PhotoFbToDtoMap;
import com.fb.app.service.fb.modelmapper.UserFbToDtoMap;

@SpringBootApplication
public class UsersPhotosLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersPhotosLoaderApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
		
		ModelMapper mp = new ModelMapper();
		mp.addMappings(new PhotoFbToDtoMap());
		mp.addMappings(new UserFbToDtoMap());
		
        return mp;
    }
}
