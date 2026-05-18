package com.example.urlshortner.service;

import com.example.urlshortner.model.Url;
import com.example.urlshortner.dto.UrlDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {

    public Url generateShortLink(UrlDto urlDto, HttpServletRequest request);
    public Url getEncodedUrl(String url);
    public void deleteShortLink(Url url);
    public Url getUrlByShortLink(String shortLink);
}
