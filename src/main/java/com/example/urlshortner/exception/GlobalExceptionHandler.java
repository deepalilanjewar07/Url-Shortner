package com.example.urlshortner.exception;

import com.example.urlshortner.dto.UrlErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UrlErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {

        String errorMessage = ex
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        UrlErrorResponseDto errorResponse =
                new UrlErrorResponseDto(
                        "400",
                        errorMessage,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }
}