package com.example.foodbe.controllers;


import com.example.foodbe.response.category.CategoryResponseDTO;
import com.example.foodbe.request.category.CreateCategoryDTO;
import com.example.foodbe.request.category.UpdateCategoryDTO;
import com.example.foodbe.payload.ApiResponse;
import com.example.foodbe.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public  ResponseEntity<ApiResponse<CategoryResponseDTO>> getById( @PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(categoryService.findById(id)));
    }


    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> create(@Valid @RequestBody CreateCategoryDTO createCategoryDTO){
        return ResponseEntity.ok(ApiResponse.success(categoryService.create(createCategoryDTO)));
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateById(@PathVariable Long id,
                                         @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO){
        return ResponseEntity.ok(ApiResponse.success(categoryService.updateById(id,updateCategoryDTO)));
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return  ResponseEntity.ok(ApiResponse.success("xoa thanh cong id: "+id));
    }




}
