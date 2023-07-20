package com.application.blogger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogger.response.JwtAuthRequest;
import com.application.blogger.response.JwtAuthResponse;
import com.application.blogger.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(
			@RequestBody JwtAuthRequest request
			){
		
		this.authenticate(request.getUsername(), request.getPassword());
		
		UserDetails userDetails =this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse().builder().token(token).build();
		//response.setToken(token);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(usernamePassword);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or password");
		}
	
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials invalid";
	}

}

























