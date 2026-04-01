package com.dev.blogging.services;

import java.util.List;

import com.dev.blogging.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,int id);
	
	UserDto getUserById(int id);
	
	List<UserDto> getAllUsers();
	
	void deletUser(int id);
}
