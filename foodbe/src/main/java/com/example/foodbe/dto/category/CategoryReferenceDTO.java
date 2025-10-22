package com.example.foodbe.dto.category;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryReferenceDTO {
    private Long id;
    private String name;
    private String slug;
}
