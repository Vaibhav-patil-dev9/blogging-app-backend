package com.dev.blogging.payloads;

import java.util.HashSet;
import java.util.Set;

import com.dev.blogging.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private String addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments  = new HashSet<>();
	
	

}
