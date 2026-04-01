package com.dev.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.blogging.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
