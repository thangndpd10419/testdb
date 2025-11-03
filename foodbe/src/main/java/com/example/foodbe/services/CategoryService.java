package com.example.foodbe.services;

import com.example.foodbe.dto.CategoryRequestDTO;
import com.example.foodbe.dto.category.CategoryResponseDTO;
import com.example.foodbe.dto.category.CreateCategoryDTO;
import com.example.foodbe.dto.category.UpdateCategoryDTO;

import java.util.List;


public interface CategoryService {
    List<CategoryResponseDTO> findAll();

    CategoryResponseDTO findById(Long id);
    CategoryResponseDTO create(CreateCategoryDTO createCategoryDTO);
    CategoryResponseDTO updateById(Long id,UpdateCategoryDTO updateCategoryDTO);
    void deleteById(Long id);
}
