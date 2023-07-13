package com.application.blogger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogger.dto.CommentDto;
import com.application.blogger.response.ApiResponse;
import com.application.blogger.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentConroller {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId){
		
		CommentDto commentDto = this.commentService.createComment(comment, postId);
		return new ResponseEntity<>(commentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
		
		this.commentService.deleteComment(id);
		return new ResponseEntity<>(new ApiResponse("comment has been deleted", true), HttpStatus.OK);
		
	}

}



















