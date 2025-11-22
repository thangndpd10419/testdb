package com.example.foodbe.dto.category;

import lombok.*;

import java.time.LocalDateTime;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String slug;
    private String imgCategory;
    private LocalDateTime createAt;
}



