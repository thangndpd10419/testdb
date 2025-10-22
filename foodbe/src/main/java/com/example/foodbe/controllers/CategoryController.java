package com.example.foodbe.controllers;


import com.example.foodbe.dto.CategoryRequestDTO;
import com.example.foodbe.dto.category.CategoryResponseDTO;
import com.example.foodbe.dto.category.CreateCategoryDTO;
import com.example.foodbe.dto.category.UpdateCategoryDTO;
import com.example.foodbe.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDTO> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getById(@Valid @PathVariable Long id){
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryResponseDTO create(@RequestBody CreateCategoryDTO createCategoryDTO){
        return categoryService.create(createCategoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateById(@PathVariable Long id,
                                         @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO){
        return categoryService.update(id,updateCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
    }




}
