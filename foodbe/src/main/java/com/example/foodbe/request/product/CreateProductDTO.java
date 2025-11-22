package com.example.foodbe.request.product;

import com.example.foodbe.annotation.FormatWhitespace;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateProductDTO {

    @FormatWhitespace
    @NotBlank(message = "name not empty")
    private String name;
    @NotBlank(message = "slug not empty")
    private String slug;
    @NotNull(message = "price must not be null")
    @Positive(message = "price must be greater than 0")
    private BigDecimal price;
    @NotBlank(message = "img not empty")
    private String imgProduct;

    private String description;
    @NotNull(message = "id category must not be null")
    private Long categoryId;
}
