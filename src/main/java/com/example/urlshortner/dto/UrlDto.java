package com.example.urlshortner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class UrlDto {

    //postman messages
    @NotBlank(message = "URL cannot be empty")

    @Pattern(
            regexp = "^(https?://).+",
            message = "URL must start with https:// or https://"
    )

    private String url;
    private LocalDateTime expirationDate;

    public UrlDto() {
    }

    public UrlDto(String url, LocalDateTime expirationDate) {
        this.url = url;
        this.expirationDate = expirationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}