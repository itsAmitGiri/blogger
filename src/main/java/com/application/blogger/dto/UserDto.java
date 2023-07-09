package com.application.blogger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="username must be atleast 4 characters")
	private String name;
	
	@Email(message="please enter corrent email")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=15)
	private String password; 
	
	@NotNull
	private String about;

}
