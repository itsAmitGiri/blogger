package com.application.blogger.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.application.blogger.model.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {


}
