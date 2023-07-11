package com.application.blogger.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogger.dto.PostDto;
import com.application.blogger.exception.ResourceNotFoundException;
import com.application.blogger.model.CategoryEntity;
import com.application.blogger.model.PostEntity;
import com.application.blogger.model.UserEntity;
import com.application.blogger.repository.CategoryRepo;
import com.application.blogger.repository.PostRepo;
import com.application.blogger.repository.UserRepo;
import com.application.blogger.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		
		UserEntity userEntity = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id",userId));
		
		CategoryEntity categoryEntity = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id",categoryId));
		
		PostEntity postEntity = this.modelMapper.map(postDto, PostEntity.class);
		postEntity.setImageName("default.png");
		postEntity.setAddedDate(new Date());
		postEntity.setCategory(categoryEntity);
		postEntity.setUser(userEntity);
		PostEntity savedPost = this.postRepo.save(postEntity);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		// TODO Auto-generated method stub
		
		//UserEntity userEntity = this.userRepo.findById(userId)
				//.orElseThrow(() -> new ResourceNotFoundException("Post","id",userId));
		
		//CategoryEntity categoryEntity = this.categoryRepo.findById(categoryId)
				//.orElseThrow(() -> new ResourceNotFoundException("Post","id",categoryId));
		
		
		PostEntity postEntity = this.postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
		postEntity.setPostTitle(postDto.getPostTitle());
		postEntity.setContent(postDto.getContent());
		postEntity.setImageName("default.png");
		postEntity.setAddedDate(new Date());
		//postEntity.setCategory(categoryEntity);
		//postEntity.setUser(userEntity);
		PostEntity updatedPost = this.postRepo.save(postEntity);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		// TODO Auto-generated method stub
		PostEntity postEntity = this.postRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
		this.postRepo.delete(postEntity);
		
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		List<PostEntity> postEntity = this.postRepo.findAll();
		List<PostDto> postDto = postEntity
				.stream()
				.map((p)->this.modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public PostDto getPostById(Integer id) {
		// TODO Auto-generated method stub
		PostEntity postEntity = this.postRepo.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("post","id",id));
		
		return this.modelMapper.map(postEntity, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		CategoryEntity category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
		List<PostEntity> postEntity = this.postRepo.findByCategory(category);
		List<PostDto> postDto = postEntity
				.stream().map((e) -> this.modelMapper.map(e, PostDto.class))
				.collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		List<PostEntity> postEntity = this.postRepo.findByUser(user);
		List<PostDto> postDto = postEntity
				.stream().map((e) -> this.modelMapper.map(e, PostDto.class))
				.collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
