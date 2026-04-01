package com.dev.blogging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.blogging.entities.User;
import com.dev.blogging.exceptions.ResourceNotFoundException;
import com.dev.blogging.payloads.UserDto;
import com.dev.blogging.repositories.UserRepo;
import com.dev.blogging.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		userRepo.save(user);
		return userToDto(user);	
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);
		
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto> userDtos=users.stream()
				.map(user -> this.userToDto(user))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deletUser(int id) {
		User user = userRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("User", "Id", id));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
////		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	
	private UserDto userToDto(User user)
	{
		UserDto userDto = modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
////		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
