package com.example.foodbe.request.category;

import com.example.foodbe.annotation.FormatWhitespace;
import com.example.foodbe.annotation.Trim;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateCategoryDTO {

    @FormatWhitespace
    @NotBlank(message = "name not empty")
    private String name;

    @Trim
    @NotBlank(message = "slug not empty")
    private String slug;

    @Trim
    @NotBlank(message = "imgCategory not empty")
    private String imgCategory;

}
