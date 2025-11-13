package com.example.foodbe.exception_handler;

import com.example.foodbe.payload.ApiError;
import com.example.foodbe.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import jakarta.servlet.http.HttpServletRequest;


import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler {

    // ==================== NotFoundException (404) ====================
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        log.error("NotFoundException: {}", ex.getMessage());
        int status = HttpStatus.NOT_FOUND.value();
        ApiError error = new ApiError(status, ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(ApiResponse.error(status,error,ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    // ==================== ConflictException (409) ====================
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleConflictException(ConflictException ex, HttpServletRequest request) {
        log.error("ConflictException: {}", ex.getMessage());
        int status = HttpStatus.CONFLICT.value();
        ApiError error = new ApiError(status, ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(ApiResponse.error(status,error,ex.getMessage()),HttpStatus.CONFLICT);

    }

    // ==================== Validation errors (400) ====================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("ValidationException: {}", ex.getMessage());
        String message =  ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(";"));


        int status = HttpStatus.BAD_REQUEST.value();
        ApiError error = new ApiError(status, message, request.getRequestURI());
        return new ResponseEntity<>(ApiResponse.error(status,error,ex.getMessage()),HttpStatus.BAD_REQUEST);

    }

    // ==================== AccessDeniedException (403) ====================
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.error("AccessDeniedException: {}", ex.getMessage());
        int status = HttpStatus.FORBIDDEN.value();
        ApiError error = new ApiError(status, "Bạn không có quyền truy cập", request.getRequestURI());
        return new ResponseEntity<>(ApiResponse.error(status,error,ex.getMessage()),HttpStatus.FORBIDDEN);

    }

    // ==================== Generic Exception (500) ====================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ApiError>> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unhandled exception: ", ex);
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        ApiError error = new ApiError(status, "Có lỗi xảy ra, vui lòng thử lại sau", request.getRequestURI());
        return new ResponseEntity<>(ApiResponse.error(status,error,ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
