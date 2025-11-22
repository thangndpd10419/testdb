package com.example.foodbe.request.product;

import com.example.foodbe.annotation.FormatWhitespace;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateProductDTO {

    @FormatWhitespace
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
