package com.example.urlshortner.controller;

import com.example.urlshortner.dto.SuccessResponseDto;
import com.example.urlshortner.dto.UrlDto;
import com.example.urlshortner.dto.UrlErrorResponseDto;
import com.example.urlshortner.dto.UrlResponseDto;
import com.example.urlshortner.exception.UrlExpiredException;
import com.example.urlshortner.kafka.ClickEventProducer;
import com.example.urlshortner.kafka.event.ClickEvent;
import com.example.urlshortner.model.Url;
import com.example.urlshortner.service.RateLimiterService;
import com.example.urlshortner.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class UrlShorteningController {

    @Autowired
    private ClickEventProducer clickEventProducer;

    @Autowired
    private RateLimiterService rateLimiterService;

    private final UrlService urlService;

    public UrlShorteningController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShortLink(
            @Valid @RequestBody UrlDto urlDto,
            HttpServletRequest request
    )
    {
        System.out.println("API HIT");
        try {

            System.out.println("REQUEST RECEIVED: " + urlDto.getUrl());

            String ip = request.getRemoteAddr();

            if (!rateLimiterService.isAllowed(ip)) {
                return ResponseEntity.status(429).body("Rate limit exceeded");
            }

            Url url = urlService.generateShortLink(urlDto, request);

             UrlResponseDto response = new UrlResponseDto(
                            url.getOriginalUrl(),
                            url.getShortLink(),
                            url.getCreationDate(),
                            url.getExpirationDate()
                    );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 THIS WILL SHOW REAL ERROR
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl(
            @PathVariable String shortLink, HttpServletRequest request
    ) {

        try {

            Url url = urlService.getEncodedUrl(shortLink);
            ClickEvent event = new ClickEvent(
                    shortLink,
                    request.getRemoteAddr(),
                    System.currentTimeMillis()
            );

            clickEventProducer.sendClickEvent(event);

            if (url == null) {
                UrlErrorResponseDto errorResponse =
                        new UrlErrorResponseDto(
                                "404",
                                "Short URL not found",
                                LocalDateTime.now()
                        );

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(errorResponse);
            }

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(URI.create(url.getOriginalUrl()))
                    .build();

        } catch (UrlExpiredException ex) {

            UrlErrorResponseDto errorResponse =
                    new UrlErrorResponseDto(
                            "410",
                            "Link has expired",
                            LocalDateTime.now()
                    );

            return ResponseEntity
                    .status(HttpStatus.GONE)
                    .body(errorResponse);
        }
    }

    @DeleteMapping("/{shortLink}")
    public ResponseEntity<?> deleteShortLink(
            @PathVariable String shortLink
    ) {

        Url url = urlService.getUrlByShortLink(shortLink);

        if (url == null) {

            UrlErrorResponseDto errorResponse =
                    new UrlErrorResponseDto(
                            "404",
                            "Short URL not found",
                            LocalDateTime.now()
                    );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }

        urlService.deleteShortLink(url);

        SuccessResponseDto response =
                new SuccessResponseDto(
                        "200",
                        "Short URL deleted successfully",
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }
}