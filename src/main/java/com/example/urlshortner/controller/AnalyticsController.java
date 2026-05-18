package com.example.urlshortner.controller;

import com.example.urlshortner.dto.AnalyticsResponseDto;
import com.example.urlshortner.model.Url;
import com.example.urlshortner.repository.UrlRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final UrlRepository urlRepository;

    public AnalyticsController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping("/{shortLink}")
    public AnalyticsResponseDto getAnalytics(@PathVariable String shortLink) {

        Url url = urlRepository.findByShortLink(shortLink)
                .orElse(null);
        if (url == null) {
            return null;
        }

        return new AnalyticsResponseDto(
                url.getOriginalUrl(),
                url.getShortLink(),
                url.getClickCount(),
                url.getCreationDate(),
                url.getLastAccessed(),
                url.getExpirationDate()
        );
    }
}