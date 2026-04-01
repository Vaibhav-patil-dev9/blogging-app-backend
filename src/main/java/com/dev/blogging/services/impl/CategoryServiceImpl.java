package com.dev.blogging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.blogging.entities.Category;
import com.dev.blogging.exceptions.ResourceNotFoundException;
import com.dev.blogging.payloads.CategoryDto;
import com.dev.blogging.repositories.CategoryRepo;
import com.dev.blogging.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category addedCat = categoryRepo.save(this.modelMapper.map(categoryDto,Category.class));
		return modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category existingCategory = this.categoryRepo.findById(categoryId)
				.orElseThrow( ()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		modelMapper.map(categoryDto, existingCategory);
		existingCategory.setCategoryId(categoryId);
		Category updatedCategory = categoryRepo.save(existingCategory);
		
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow( ()->new ResourceNotFoundException("Category", "Id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categoryList = categoryRepo.findAll();
		
		List<CategoryDto> categoryDtoList = categoryList.stream()
			.map(category -> modelMapper.map(category, CategoryDto.class))
			.collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow( () -> new ResourceNotFoundException("Catogory", "Id", categoryId));
		
		this.categoryRepo.delete(category);
	}

}
