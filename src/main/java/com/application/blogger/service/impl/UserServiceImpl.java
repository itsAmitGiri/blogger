package com.application.blogger.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogger.dto.UserDto;
import com.application.blogger.exception.GlobalExceptionHandler;
import com.application.blogger.exception.ResourceNotFoundException;
import com.application.blogger.model.UserEntity;
import com.application.blogger.repository.UserRepo;
import com.application.blogger.service.UserService;

@Service
public class UserServiceImpl implements UserService  {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto user){
		// TODO Auto-generated method stub
		UserEntity userEntity = this.dtoToUser(user);
		UserEntity savedUser = userRepo.save(userEntity);
		
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("UserEntity","id", userId));
		
		userEntity.setUserName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setAbout(user.getAbout());
		
		UserEntity updatedUser = this.userRepo.save(userEntity);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("UserEntity","id", id));
		return this.userToUserDto(userEntity);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<UserEntity> userEntity = this.userRepo.findAll();
		List<UserDto> users = userEntity.stream()
				.map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		
				
		return users;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.userRepo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", id));
		
		this.userRepo.delete(userEntity);
		
	}
	
	private UserEntity dtoToUser(UserDto userDto) {
		UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
//		userEntity.setUserId(userDto.getId());
//		userEntity.setUserName(userDto.getName());
//		userEntity.setEmail(userDto.getEmail());
//		userEntity.setPassword(userDto.getPassword());
//		userEntity.setAbout(userDto.getAbout());
		return userEntity;
	}
	
	private UserDto userToUserDto(UserEntity userEntity) {
		UserDto userDto = this.modelMapper.map(userEntity, UserDto.class);
//		userDto.setId(userEntity.getUserId());
//		userDto.setName(userEntity.getUserName());
//		userDto.setEmail(userEntity.getEmail());
//		userDto.setPassword(userEntity.getPassword());
//		userDto.setAbout(userEntity.getAbout());
		return userDto;
	}

}
