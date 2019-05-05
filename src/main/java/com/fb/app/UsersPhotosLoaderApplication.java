package com.fb.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersPhotosLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersPhotosLoaderApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
