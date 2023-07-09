package com.application.blogger.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogger.dto.ApiResponse;
import com.application.blogger.dto.UserDto;
import com.application.blogger.model.UserEntity;
import com.application.blogger.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/allusers")
	public List<UserDto> findAllUsers() {
		List<UserDto> userDto = this.userService.getAllUser();
		return userDto;
	}
	
	@GetMapping("/{id}")
	public UserDto findUser(@PathVariable Integer id) {
		UserDto userDto = this.userService.getUserById(id);
		return userDto;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, 
			@PathVariable Integer id){
		UserDto updatedUserDto = this.userService.updateUser(user, id);
		return ResponseEntity.ok(updatedUserDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
		
		this.userService.deleteUser(id);
		return ResponseEntity.ok(new ApiResponse("User Deleted successfully", true));
		
	}

}













