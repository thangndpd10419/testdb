package com.example.foodbe.controllers;


import com.example.foodbe.dto.CategoryRequestDTO;
import com.example.foodbe.dto.category.CategoryResponseDTO;
import com.example.foodbe.dto.category.CreateCategoryDTO;
import com.example.foodbe.dto.category.UpdateCategoryDTO;
import com.example.foodbe.models.Category;
import com.example.foodbe.payload.ApiResponse;
import com.example.foodbe.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>>  getAll(){
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<CategoryResponseDTO>> getById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(categoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> create(@RequestBody CreateCategoryDTO createCategoryDTO){
        return ResponseEntity.ok(ApiResponse.success(categoryService.create(createCategoryDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateById(@PathVariable Long id,
                                         @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO){
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id,updateCategoryDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return  ResponseEntity.ok(ApiResponse.success("xoa thanh cong id: "+id));
    }




}
