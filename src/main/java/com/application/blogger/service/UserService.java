package com.application.blogger.service;

import java.util.List;

import com.application.blogger.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
	
}
