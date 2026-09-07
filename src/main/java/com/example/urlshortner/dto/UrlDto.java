package com.example.urlshortner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
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

}