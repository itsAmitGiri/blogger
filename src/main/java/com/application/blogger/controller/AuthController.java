package com.application.blogger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogger.response.JwtAuthRequest;
import com.application.blogger.response.JwtAuthResponse;
import com.application.blogger.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//@Autowired
	//private AuthenticationManager authenticationManager; 
	
//	@PostMapping("/login")
//	public ResponseEntity<JwtAuthResponse> createToken(
//			@RequestBody JwtAuthRequest request
//			){
//		
//		this.authenticate(request.getUsername(), request.getPassword());
//		
//	}
//	
//	private void authenticate(String username, String password) {
//		
//		
//		
//	}

}
