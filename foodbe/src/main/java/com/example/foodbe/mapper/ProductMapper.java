package com.example.foodbe.mapper;


import com.example.foodbe.dto.product.CreateProductDTO;
import com.example.foodbe.dto.product.ProductResponseDTO;
import com.example.foodbe.models.Category;
import com.example.foodbe.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public ProductResponseDTO toDto(Product product){
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .price(product.getPrice())
                .imgProduct(product.getImgProduct())
                .description(product.getDescription())
                .categoryReferenceDTO(categoryMapper.toReference(product.getCategory()))
                .build();
    }

    public Product toEntity(CreateProductDTO createProductDTO,Category category){

        return Product.builder()
                .name(createProductDTO.getName())
                .slug(createProductDTO.getSlug())
                .price(createProductDTO.getPrice())
                .imgProduct(createProductDTO.getImgProduct())
                .description(createProductDTO.getDescription())
                .category(category)
                .build();
    }


}
