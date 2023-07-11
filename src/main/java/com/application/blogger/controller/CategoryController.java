package com.application.blogger.controller;

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

import com.application.blogger.dto.CategoryDto;
import com.application.blogger.response.ApiResponse;
import com.application.blogger.service.CategoryService;
import com.application.blogger.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto category){
		
		CategoryDto res = this.categoryService.createCategory(category);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update-category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category, @PathVariable Integer id){
		
		CategoryDto res = this.categoryService.updateCategory(category, id);
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete-category/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
		this.categoryService.deleteCategory(id);
		ApiResponse response = new ApiResponse("category has been deleted", true);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
		CategoryDto res = this.categoryService.getCategoryById(id);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> res = this.categoryService.getAllCategory();
		
		return ResponseEntity.ok(res);
	}
	

}




























