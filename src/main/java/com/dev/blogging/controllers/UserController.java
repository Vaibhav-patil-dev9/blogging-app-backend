package com.dev.blogging.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.dev.blogging.entities.User;
import com.dev.blogging.payloads.ApiResponse;
import com.dev.blogging.payloads.UserDto;
import com.dev.blogging.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService ;
	
	// POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto,HttpStatus.CREATED);
	}
	
	// GET - get a single user by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int id)
	{
		UserDto userDto = userService.getUserById(id);
		if(userDto!=null) {
			return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
		}
	}
	
	
	// GET - get list of users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> users = userService.getAllUsers();
		if(users != null)
		{
			return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//PUT - update user by id
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable int id){
		UserDto updatedUserDto = userService.updateUser(userDto, id);
		if(updatedUserDto != null) 
		{
			return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
		}else {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
	}
	
	// DELETE - delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
		userService.deletUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
		
}
