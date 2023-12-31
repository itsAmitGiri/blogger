package com.application.blogger.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.blogger.dto.PostDto;
import com.application.blogger.model.PostEntity;
import com.application.blogger.response.ApiResponse;
import com.application.blogger.response.PostResponse;
import com.application.blogger.service.FileService;
import com.application.blogger.service.PostService;
import com.application.blogger.util.AppConstants;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileServie;
	
	@Value("${project.image.path}")
	private String path;
	
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
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY, required=false) String sortBy,
			@RequestParam(value="sortType", defaultValue=AppConstants.SORT_TYPE, required=false) String sortType
			){
		PostResponse posts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortType);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
		this.postService.deletePost(id);
		return new ResponseEntity<>(new ApiResponse("Post has been deleted", true), HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
		List<PostDto> searchPosts = this.postService.searchPost(keyword);
		
		return new ResponseEntity<>(searchPosts, HttpStatus.OK);
	}
	
	@PutMapping("/image/upload/{id}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer id)throws IOException{
		PostDto postDto = this.postService.getPostById(id);

		String fileName = this.fileServie.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, id);
		
		return new ResponseEntity<>(updatedPost, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable String imageName,
			HttpServletResponse response)throws IOException {
		
		InputStream resource = this.fileServie.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
}

















