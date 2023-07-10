package com.application.blogger.dto;

import java.util.Date;

import com.application.blogger.model.CategoryEntity;
import com.application.blogger.model.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer id;
	
	private String postTitle;
	
	private String content;
	
	private String imageName;
		
	private CategoryDto category;
	
	private UserDto user;
	
	
}
