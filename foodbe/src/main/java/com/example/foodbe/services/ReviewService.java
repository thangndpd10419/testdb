package com.example.foodbe.services;

import com.example.foodbe.dto.review.CreateReviewDto;
import com.example.foodbe.dto.review.ReviewResponseDto;
import com.example.foodbe.dto.review.UpdateReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewResponseDto> findAll();
    ReviewResponseDto create(CreateReviewDto createReviewDto);
    ReviewResponseDto updateById(Long id,UpdateReviewDto updateReviewDto);
    void deleteById(Long id);

}
