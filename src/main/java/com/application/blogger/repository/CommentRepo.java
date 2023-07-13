package com.application.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogger.model.CommentEntity;

public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {

}
