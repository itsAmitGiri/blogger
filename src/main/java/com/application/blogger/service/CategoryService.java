package com.application.blogger.service;

import java.util.List;

import com.application.blogger.dto.CategoryDto;
import com.application.blogger.model.CategoryEntity;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto category);
	
	CategoryDto updateCategory(CategoryDto category, Integer id);
	
	void deleteCategory(Integer id);
	
	List<CategoryDto> getAllCategory();
	
	CategoryDto getCategoryById(Integer id);

}
