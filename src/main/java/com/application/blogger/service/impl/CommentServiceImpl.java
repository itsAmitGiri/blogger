package com.application.blogger.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogger.dto.CommentDto;
import com.application.blogger.exception.ResourceNotFoundException;
import com.application.blogger.model.CommentEntity;
import com.application.blogger.model.PostEntity;
import com.application.blogger.repository.CommentRepo;
import com.application.blogger.repository.PostRepo;
import com.application.blogger.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		PostEntity postEntity = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("post","id",postId));
		
		CommentEntity commentEntity = this.modelMapper.map(commentDto, CommentEntity.class);

		commentEntity.setPost(postEntity);
		//commentEntity.setUser(postEntity.getUser());
		CommentEntity savedComment = (CommentEntity) this.commentRepo.save(commentEntity);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer id) {
		// TODO Auto-generated method stub
		CommentEntity comment = this.commentRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("post","id",id));
		this.commentRepo.delete(comment);
	}

}
