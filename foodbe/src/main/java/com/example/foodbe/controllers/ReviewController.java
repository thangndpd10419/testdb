package com.example.foodbe.controllers;


import com.example.foodbe.dto.review.CreateReviewDto;
import com.example.foodbe.dto.review.ReviewResponseDto;
import com.example.foodbe.dto.review.UpdateReviewDto;
import com.example.foodbe.payload.ApiResponse;
import com.example.foodbe.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponseDto>>> findAll(){
        return ResponseEntity.ok(ApiResponse.success(reviewService.findAll()));
    }

    @PreAuthorize("hasRole('User')")
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponseDto>> create(@Valid @RequestBody CreateReviewDto createReviewDto){
        return ResponseEntity.ok(ApiResponse.success(reviewService.create(createReviewDto)));
    }

    @PreAuthorize("hasRole('User')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponseDto>> updateById(@PathVariable Long id, @Valid @RequestBody UpdateReviewDto updateReviewDto){
        return ResponseEntity.ok(ApiResponse.success(reviewService.updateById(id,updateReviewDto)));
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id){
        reviewService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Delete success"));
    }




}
