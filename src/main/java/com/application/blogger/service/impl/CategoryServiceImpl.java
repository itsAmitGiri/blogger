package com.application.blogger.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogger.dto.CategoryDto;
import com.application.blogger.exception.ResourceNotFoundException;
import com.application.blogger.model.CategoryEntity;
import com.application.blogger.repository.CategoryRepo;
import com.application.blogger.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		// TODO Auto-generated method stub
		CategoryEntity categoryEntity = this.modelMapper.map(category, CategoryEntity.class);
		CategoryEntity savedEntity = this.categoryRepo.save(categoryEntity);
		
		return this.modelMapper.map(savedEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer id) {
		// TODO Auto-generated method stub
		
		CategoryEntity categoryEntity = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "id", id));
		
		categoryEntity.setCategoryTitle(category.getCategoryTitle());
		categoryEntity.setDescription(category.getDescription());
		
		CategoryEntity updatedCategory = this.categoryRepo.save(categoryEntity);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		CategoryEntity category = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "id", id));
		
		this.categoryRepo.delete(category);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<CategoryEntity> categoryEntities = this.categoryRepo.findAll();
		List<CategoryDto> list = categoryEntities.stream()
				.map(cat -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		
		
		return list;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		// TODO Auto-generated method stub
		CategoryEntity categoryEntity = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "id", id));
		
		return this.modelMapper.map(categoryEntity, CategoryDto.class);
	}
	

}
