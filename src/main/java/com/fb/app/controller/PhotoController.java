package com.fb.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fb.app.dto.PhotoDto;
import com.fb.app.service.PhotoService;


import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
public class PhotoController {

	private static final int MAX_PAGE_SIZE = 10;
	
	@Autowired
	PhotoService photoService;
	
	@GetMapping("/users/{user_fb_id}/photos")
	public ResponseEntity<List<PhotoDto>> getUserPhotos(
			@PathVariable(name="user_fb_id") String userFbId,
			@PageableDefault(size = MAX_PAGE_SIZE) Pageable pageable) {
		
		final PageRequest pr = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize()
		);
		
		Page<PhotoDto> usersPage = photoService.getAllUserTaggedPhotos(userFbId, pr);
		
		if(usersPage.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			long totalBooks = usersPage.getTotalElements();
            int nbPageBooks = usersPage.getNumberOfElements();

            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalBooks));
            HttpStatus httpStatus;
            if (nbPageBooks < totalBooks) {
                headers.add("first", buildPageUri(userFbId, PageRequest.of(0, usersPage.getSize())));
                headers.add("last", buildPageUri(userFbId, PageRequest.of(usersPage.getTotalPages() - 1, usersPage.getSize())));

                if (usersPage.hasNext())
                    headers.add("next", buildPageUri(userFbId, usersPage.nextPageable()));

                if (usersPage.hasPrevious())
                    headers.add("prev", buildPageUri(userFbId, usersPage.previousPageable()));
                httpStatus = HttpStatus.PARTIAL_CONTENT;
                
            } else {
                httpStatus = HttpStatus.OK;
            }
            
            return new ResponseEntity<>(usersPage.getContent(), headers, httpStatus);
		}
	}
	
	private String buildPageUri(String userFbId, Pageable page) {
        return fromUriString("/users/{user_fb_id}/photos")
                .query("page={page}&size={size}")
                .buildAndExpand(userFbId, page.getPageNumber(), page.getPageSize())
                .toUriString();
	}
}
