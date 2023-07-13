package com.application.blogger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.application.blogger.exception.ResourceNotFoundException;
import com.application.blogger.model.UserEntity;
import com.application.blogger.repository.UserRepo;

public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserEntity user = this.userRepo.findByEmail(username)
			.orElseThrow(()-> new ResourceNotFoundException("User", "email "+username,0));
		
		return user;
	}

}
