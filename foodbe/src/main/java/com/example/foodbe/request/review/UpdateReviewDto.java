package com.example.foodbe.dto.review;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReviewDto {

    @Min(value = 1,message = "đánh giá >=1")
    @Max(value = 5, message = "đánh giá ,=5")
    private int rating;       // Điểm đánh giá mới
    @NotBlank(message = "Comment không để trống")
    @Size(max = 500, message = "Comment không quá 500 kí tự")
    private String comment;   // Nội dung đánh giá mới

}
