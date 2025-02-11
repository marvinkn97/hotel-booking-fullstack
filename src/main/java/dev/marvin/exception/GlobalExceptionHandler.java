package dev.marvin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse<Object>> handleResourceNotFound(ResourceNotFoundException e){
        ErrorResponse<Object> errorResponse = ErrorResponse.builder()
                .timestamp(new Date())
                .success(false)
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errorDetail(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
