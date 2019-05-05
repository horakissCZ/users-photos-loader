package com.fb.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fb.app.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	@Query("SELECT p FROM Photo p JOIN p.users u WHERE u.userFbId = :userFbId")
	List<Photo> findByUserFbId(String userFbId);
}
