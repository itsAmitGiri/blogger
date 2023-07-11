package com.application.blogger.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogger.dto.PostDto;
import com.application.blogger.model.PostEntity;
import com.application.blogger.response.ApiResponse;
import com.application.blogger.response.PostResponse;
import com.application.blogger.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostDto post = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer id){
		PostDto post = this.postService.updatePost(postDto,id);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer id){
		
		List<PostDto> posts = this.postService.getAllPostsByUser(id);
		return new ResponseEntity<>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategories(@PathVariable Integer id){
		List<PostDto> posts = this.postService.getAllPostsByCategory(id);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id){
		PostDto postDto = this.postService.getPostById(id);
		return new ResponseEntity<>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getPosts(
			@RequestParam(value="pageNumber", defaultValue="0", required=false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="2", required=false) Integer pageSize
			){
		PostResponse posts = this.postService.getAllPosts(pageNumber, pageSize);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
		this.postService.deletePost(id);
		return new ResponseEntity<>(new ApiResponse("Post has been deleted", true), HttpStatus.OK);
	}
}

















