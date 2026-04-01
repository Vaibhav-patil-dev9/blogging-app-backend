package com.dev.blogging.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.blogging.entities.Comment;
import com.dev.blogging.entities.Post;
import com.dev.blogging.exceptions.ResourceNotFoundException;
import com.dev.blogging.payloads.CommentDto;
import com.dev.blogging.repositories.CommentRepo;
import com.dev.blogging.repositories.PostRepo;
import com.dev.blogging.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		Comment comment =modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment =commentRepo.save(comment);
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = commentRepo.findById(commentId).orElseThrow( ()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		commentRepo.delete(com);
	}

}
