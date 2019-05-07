package com.fb.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fb.app.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	@Query("SELECT p FROM Photo p JOIN p.users u WHERE u.userFbId = :userFbId")
	Page<Photo> findByUserFbId(String userFbId, Pageable pageable);
	
	@Query("SELECT p FROM Photo p LEFT JOIN p.users u WHERE u.userFbId is null")
	List<Photo> findPhotosWithoutUser();
}
