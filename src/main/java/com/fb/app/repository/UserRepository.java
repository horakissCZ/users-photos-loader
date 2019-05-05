package com.fb.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fb.app.domain.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {

	@Query("SELECT u FROM User u WHERE userFbId = :userFbId")
	User findByUserFbId(String userFbId);
	
}
