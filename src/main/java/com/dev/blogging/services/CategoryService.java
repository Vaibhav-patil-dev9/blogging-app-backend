package com.dev.blogging.services;

import java.util.List;

import com.dev.blogging.payloads.CategoryDto;

public interface CategoryService {
	//bydefault in the interface all the method are public 
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	
	CategoryDto getCategoryById(int categoryId);
	
	List<CategoryDto> getAllCategory();
	
	void deleteCategory(int categoryId);
}
