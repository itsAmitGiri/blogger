package com.application.blogger.service;

import java.util.List;

import com.application.blogger.dto.PostDto;
import com.application.blogger.model.CategoryEntity;
import com.application.blogger.model.UserEntity;


public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer id);
	
	void deletePost(Integer id);
	
	List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
	
	PostDto getPostById(Integer id);

	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	List<PostDto> getAllPostsByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);
	

}
