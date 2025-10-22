package com.example.foodbe.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int status;
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;


    public static <T> ApiResponse<T> success(T data){
        return ApiResponse.<T>builder()
                .status(200)
                .success(true)
                .message("SUCCESS")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
