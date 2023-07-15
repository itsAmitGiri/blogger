package com.application.blogger.response;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String username;
	private String password;

}
