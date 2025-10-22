package com.example.foodbe.dto.product;

import com.example.foodbe.dto.category.CategoryReferenceDTO;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String slug;
    private BigDecimal price;
    private String imgProduct;
    private String description;
    private CategoryReferenceDTO categoryReferenceDTO;
}
