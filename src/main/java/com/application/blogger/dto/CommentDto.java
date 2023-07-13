package com.application.blogger.dto;

import com.application.blogger.model.PostEntity;
import com.application.blogger.model.UserEntity;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDto {
	
	private Integer id;
	
	private String comment;
	
	//private PostEntity post;
	
	//private UserEntity user;

}
