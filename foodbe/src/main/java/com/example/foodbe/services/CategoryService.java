package com.example.foodbe.services;

import com.example.foodbe.response.category.CategoryResponseDTO;
import com.example.foodbe.request.category.CreateCategoryDTO;
import com.example.foodbe.request.category.UpdateCategoryDTO;

import java.util.List;


public interface CategoryService {
    List<CategoryResponseDTO> findAll();

    CategoryResponseDTO findById(Long id);
    CategoryResponseDTO create(CreateCategoryDTO createCategoryDTO);
    CategoryResponseDTO updateById(Long id,UpdateCategoryDTO updateCategoryDTO);
    void deleteById(Long id);
}
