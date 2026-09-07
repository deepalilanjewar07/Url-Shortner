package com.example.urlshortner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SuccessResponseDto {

    private String status;
    private String message;
    private LocalDateTime timestamp;

    public SuccessResponseDto() {
    }

    public SuccessResponseDto(String status,
                              String message,
                              LocalDateTime timestamp) {

        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}