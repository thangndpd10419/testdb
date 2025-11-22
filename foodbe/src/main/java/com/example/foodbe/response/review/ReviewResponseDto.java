package com.example.foodbe.dto.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {

    private Long id;
    private int rating;
    private String comment;

    // Thông tin user cơ bản
    private Long userId;
    private String userName;

    // Thông tin product cơ bản
    private Long productId;
    private String productName;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
