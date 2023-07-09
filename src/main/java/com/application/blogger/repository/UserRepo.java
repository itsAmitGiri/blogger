package com.application.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogger.model.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
