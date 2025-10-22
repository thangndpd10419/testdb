package com.example.foodbe.dto.category;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryDTO {

    @NotBlank(message = "name not empty")
    private String name;

    @NotBlank(message = "slug not empty")
    private String slug;

    @NotBlank(message = "imgCategory not empty")
    private String imgCategory;

}
