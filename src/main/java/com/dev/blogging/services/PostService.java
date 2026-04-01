package com.dev.blogging.services;

import java.util.List;

import com.dev.blogging.entities.Post;
import com.dev.blogging.payloads.PostDto;
import com.dev.blogging.payloads.PostResponse;
import com.dev.blogging.repositories.PostRepo;

public interface PostService {
	//create
	
	PostDto createPost(PostDto postDto , Integer userId,Integer categoryId);
	
	//update post
	PostDto upadatePost(PostDto postDto ,Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get all post
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get Single post
	PostDto getPostById(Integer id);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get All post by User
	List<PostDto> getPostsByUser(Integer userId);
	
	//search post
	List<PostDto> searchPost(String keyword);
}
