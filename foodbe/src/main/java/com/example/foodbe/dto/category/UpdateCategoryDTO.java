package com.example.foodbe.dto.category;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryDTO {

    @NotBlank(message = "name not null")
    private String name;

    @NotBlank(message = "slug not null")
    private String slug;

    @NotBlank(message = "img not null")
    private String imgCategory;

}
