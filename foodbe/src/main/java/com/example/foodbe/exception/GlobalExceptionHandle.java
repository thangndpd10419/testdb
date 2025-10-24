package com.example.foodbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandle {
    // Xử lý NotFoundException (404)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        // Tạo ErrorResponse và trả về ResponseEntity với mã 404
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Xử lý ConflictException (409)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflicException(ConflictException c){
        ErrorResponse errorResponse= new ErrorResponse("CONFLIC",c.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    // Xử lý các lỗi khác (500 - Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Tạo ErrorResponse và trả về ResponseEntity với mã 500
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred.",LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Xử lý lỗi validation (400 - Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        // Xây dựng thông điệp lỗi từ các lỗi validation
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error ->
                message.append(error.getDefaultMessage()).append(" ")
        );
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", message.toString(),LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
