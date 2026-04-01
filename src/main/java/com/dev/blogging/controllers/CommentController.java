package com.dev.blogging.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.blogging.payloads.ApiResponse;
import com.dev.blogging.payloads.CommentDto;
import com.dev.blogging.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDto>  createComment(@RequestBody CommentDto commentDto,@PathVariable int postId)
	{
		CommentDto createComment = commentService.createComment(commentDto, postId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createComment);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ApiResponse("comment deleted successfully",true));
	}
}
