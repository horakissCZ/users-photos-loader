package com.fb.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fb.app.domain.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	@Query("SELECT u FROM User u WHERE userFbId = :userFbId")
	Optional<User> findByUserFbId(String userFbId);
	
}
