package com.example.foodbe.dto.review;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReviewDto {

    @Min(value = 1, message = "Rating phải từ 1 đến 5")
    @Max(value = 5, message = "Rating phải từ 1 đến 5")
    private int rating;           // Điểm đánh giá 1-5
    @NotBlank(message = "Comment không được để trống")
    @Size(max = 500, message = "Comment không quá 500 ký tự")
    private String comment;       // Nội dung đánh giá
    @NotNull
    private Long productId;       // ID sản phẩm được đánh giá
    // private Long userId;       // Có thể lấy từ token, không cần client gửi
}
