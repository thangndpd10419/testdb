package com.example.foodbe.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDTO {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String slug;
    @NotBlank
    private String imgCategory;
}


