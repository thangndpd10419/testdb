package com.example.foodbe.controllers;


import com.example.foodbe.dto.product.CreateProductDTO;
import com.example.foodbe.dto.product.ProductResponseDTO;
import com.example.foodbe.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        return productService.findAll();
    }
    @GetMapping("/{id}")
    public ProductResponseDTO getById( @PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponseDTO create(@Valid @RequestBody CreateProductDTO createProductDTO){
        return productService.create(createProductDTO);
    }
}
