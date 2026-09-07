package com.example.urlshortner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UrlErrorResponseDto {

    private String status;
    private String message;
    private LocalDateTime timestamp;

    public UrlErrorResponseDto() {
    }

    public UrlErrorResponseDto(String status,
                               String message,
                               LocalDateTime timestamp) {

        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}