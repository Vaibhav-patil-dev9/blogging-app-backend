package com.dev.blogging.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.blogging.payloads.ApiResponse;
import com.dev.blogging.payloads.CategoryDto;
import com.dev.blogging.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//GET Category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") int id){ 
		CategoryDto category =  categoryService.getCategoryById(id);
		if(category != null) {
			return ResponseEntity.status(HttpStatus.OK).body(category);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	//GET All Category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categories = categoryService.getAllCategory();
		if(categories != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categories);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	//POST Category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto category = categoryService.createCategory(categoryDto);
		if(category != null) {
			return ResponseEntity.status(HttpStatus.OK).body(category);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	//PUT - Category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") int id){
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto, id);
		if(updateCategory != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		//Modern syntax
//		return (updateCategory != null) ?  ResponseEntity.ok(updateCategory) : ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	
	//DELETE - Category
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int id) {
		categoryService.deleteCategory(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("category deleted successfully!",true));	
	}
}
