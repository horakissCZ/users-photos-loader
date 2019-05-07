package com.fb.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fb.app.dto.PhotoDto;
import com.fb.app.service.PhotoService;

@RestController
public class PhotoController {

	@Autowired
	PhotoService photoService;
	
	@GetMapping("/users/{user_fb_id}/photos")
	public ResponseEntity<List<PhotoDto>> getUserPhotos(@PathVariable(name="user_fb_id") String userFbId) {
		List<PhotoDto> userPhotos = photoService.getAllUserTaggedPhotos(userFbId);
		
		return userPhotos.isEmpty() ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(userPhotos, HttpStatus.OK);

	}
}
