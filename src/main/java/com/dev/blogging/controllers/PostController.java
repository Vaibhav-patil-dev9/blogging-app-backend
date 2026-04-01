package com.dev.blogging.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.blogging.config.AppContants;
import com.dev.blogging.payloads.ApiResponse;
import com.dev.blogging.payloads.PostDto;
import com.dev.blogging.payloads.PostResponse;
import com.dev.blogging.services.FileService;
import com.dev.blogging.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/post")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		if (createPost != null) {
			return ResponseEntity.ok(createPost);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// get posts by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postDtos = postService.getPostsByUser(userId);
		return (postDtos != null) ? ResponseEntity.ok(postDtos) : ResponseEntity.notFound().build();
	}

	// get posts by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(postDtos);
	}

	// get all posts
	@GetMapping("")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppContants.SORT_DIR, required = false) String sortDir) {
		PostResponse posts = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return ResponseEntity.ok(posts);
	}

	// get single post by id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
		PostDto post = this.postService.getPostById(postId);
		return ResponseEntity.ok(post);
	}

	// delete post
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse("Post deleted successfully", true));
	}

	// update post
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto post = postService.upadatePost(postDto, postId);
		return ResponseEntity.ok(post);
	}

	// search post
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
		List<PostDto> searchPost = postService.searchPost(keyword);
		return ResponseEntity.ok(searchPost);
	}

	// post image upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId)
			throws IOException {
		PostDto postDto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto upadatePost = postService.upadatePost(postDto, postId);
		return ResponseEntity.ok(upadatePost);
	}

	// method to serve files
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
