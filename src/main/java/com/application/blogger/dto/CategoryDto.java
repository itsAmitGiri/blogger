package com.application.blogger.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer id;
	
	@NotEmpty(message="title should not be empy")
	private String categoryTitle;
	private String description;
	
}
