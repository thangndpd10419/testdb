package com.example.foodbe.services.impls;

import com.example.foodbe.dto.product.CreateProductDTO;
import com.example.foodbe.dto.product.ProductResponseDTO;
import com.example.foodbe.mapper.CategoryMapper;
import com.example.foodbe.mapper.ProductMapper;
import com.example.foodbe.models.Category;
import com.example.foodbe.models.Product;
import com.example.foodbe.repositories.CategoryRepository;
import com.example.foodbe.repositories.ProductRepository;
import com.example.foodbe.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> findAll() {
         List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return List.of();
        }
        return  products.stream()
                .map(product->productMapper.toDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDTO create(CreateProductDTO createProductDTO) {
        // Log giá trị imgProduct
        System.out.println("===> imgProduct received: " + createProductDTO.getImgProduct());
        Category category= categoryRepository.findById(createProductDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + createProductDTO.getCategoryId()));
        Product product= productMapper.toEntity(createProductDTO,category);

        return productMapper.toDto(productRepository.save(product));
    }
}
