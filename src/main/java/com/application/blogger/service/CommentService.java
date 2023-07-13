package com.application.blogger.service;

import com.application.blogger.dto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer id);
	

}
