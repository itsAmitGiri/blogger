package com.application.blogger.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogger.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByEmail(String email);

}
