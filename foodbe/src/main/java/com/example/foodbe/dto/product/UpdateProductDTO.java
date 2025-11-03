package com.example.foodbe.dto.product;

import com.example.foodbe.dto.category.CategoryReferenceDTO;
import com.example.foodbe.models.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDTO {
    @NotBlank(message = "product name not empty")
    private String name;
    @NotBlank(message = "product slug not empty")
    private String slug;
    @NotNull(message = "price not null")
    private BigDecimal price;
    @NotBlank(message = "product img not empty")
    private String imgProduct;
    @NotBlank(message = "product description not empty")
    private String description;
    @NotNull(message = "img not null")
    private Long idCategory;
}
