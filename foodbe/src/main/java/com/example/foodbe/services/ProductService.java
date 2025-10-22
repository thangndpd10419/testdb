package com.example.foodbe.services;

import com.example.foodbe.dto.product.CreateProductDTO;
import com.example.foodbe.dto.product.ProductResponseDTO;
import com.example.foodbe.models.Category;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAll();
    ProductResponseDTO findById(Long id);
    ProductResponseDTO create(CreateProductDTO createProductDTO);
}
