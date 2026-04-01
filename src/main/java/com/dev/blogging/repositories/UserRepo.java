package com.dev.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.blogging.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
