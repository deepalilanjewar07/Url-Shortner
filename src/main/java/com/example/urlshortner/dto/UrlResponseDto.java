package com.example.urlshortner.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UrlResponseDto {

    private String originalUrl;
    private String shortLink;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    public UrlResponseDto() {
    }

    public UrlResponseDto(String originalUrl,
                          String shortLink,
                          LocalDateTime creationDate,
                          LocalDateTime expirationDate) {
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

}