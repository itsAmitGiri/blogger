package com.application.blogger.service;

import java.util.List;

import com.application.blogger.dto.PostDto;
import com.application.blogger.model.CategoryEntity;
import com.application.blogger.model.UserEntity;
import com.application.blogger.response.PostResponse;


public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer id);
	
	void deletePost(Integer id);
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortType);
	
	PostDto getPostById(Integer id);

	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	List<PostDto> getAllPostsByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);
	

}
