package com.example.urlshortner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnalyticsResponseDto {

    private String originalUrl;

    private String shortLink;

    private long clickCount;

    private LocalDateTime creationDate;

    private LocalDateTime lastAccessed;

    private LocalDateTime expirationDate;

    public AnalyticsResponseDto() {
    }

    public AnalyticsResponseDto(String originalUrl,
                                String shortLink,
                                long clickCount,
                                LocalDateTime creationDate,
                                LocalDateTime lastAccessed,
                                LocalDateTime expirationDate) {

        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.clickCount = clickCount;
        this.creationDate = creationDate;
        this.lastAccessed = lastAccessed;
        this.expirationDate = expirationDate;
    }

}