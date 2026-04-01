package com.dev.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.blogging.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
