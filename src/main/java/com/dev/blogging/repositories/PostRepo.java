package com.dev.blogging.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.blogging.entities.Category;
import com.dev.blogging.entities.Post;
import com.dev.blogging.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String keyword);
	
}
