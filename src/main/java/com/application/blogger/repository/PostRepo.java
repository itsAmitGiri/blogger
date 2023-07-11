package com.application.blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogger.model.CategoryEntity;
import com.application.blogger.model.PostEntity;
import com.application.blogger.model.UserEntity;

public interface PostRepo extends JpaRepository<PostEntity,Integer> {

	List<PostEntity> findByUser(UserEntity userEntity);
	List<PostEntity> findByCategory(CategoryEntity categoryEntity);
	
	List<PostEntity> findByPostTitleContaining(String title);
	
}
