package com.example.urlshortner.dto;

import java.time.LocalDateTime;

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

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}