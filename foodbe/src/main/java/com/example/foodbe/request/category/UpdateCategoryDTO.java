package com.example.foodbe.request.category;

import com.example.foodbe.annotation.FormatWhitespace;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateCategoryDTO {

    @FormatWhitespace
    @NotBlank(message = "name not null")
    private String name;

    @NotBlank(message = "slug not null")
    private String slug;

    @NotBlank(message = "img not null")
    private String imgCategory;

}
