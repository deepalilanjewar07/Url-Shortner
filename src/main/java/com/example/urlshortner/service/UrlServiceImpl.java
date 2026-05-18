package com.example.urlshortner.service;

import com.example.urlshortner.dto.UrlDto;
import com.example.urlshortner.model.Url;
import com.example.urlshortner.model.User;
import com.example.urlshortner.repository.UrlRepository;
import com.example.urlshortner.repository.UserRepository;
import com.example.urlshortner.util.Base62;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final UrlRepository urlRepository;

    @Autowired
    private UserRepository userRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // CREATE SHORT URL
    @Override
    public Url generateShortLink(UrlDto urlDto, HttpServletRequest request) {

        Url existing = urlRepository.findByOriginalUrl(urlDto.getUrl())
                .orElse(null);

        if (existing != null) {
            return existing;
        }

        String username = (String) request.getAttribute("username");
        User user = userRepository.findByUsername(username);

        Url url = new Url();

        url.setOriginalUrl(urlDto.getUrl());
        url.setCreationDate(LocalDateTime.now());
        url.setExpirationDate(urlDto.getExpirationDate());
        url.setUser(user);

        // FIRST SAVE
        url = urlRepository.saveAndFlush(url);

        // GENERATE SHORT LINK
        String shortLink = Base62.encode(url.getId());

        url.setShortLink(shortLink);

        // SECOND SAVE
        url = urlRepository.saveAndFlush(url);

        updateCache(shortLink, url.getOriginalUrl(), url);

        return url;
    }

    // REDIRECT LOGIC
    @Override
    public Url getEncodedUrl(String shortLink) {

        String cachedUrl = getFromCache(shortLink);

        Url url;

        if (cachedUrl != null) {
            System.out.println("SOURCE: REDIS");

            url = urlRepository.findByShortLink(shortLink)
                    .orElse(null);

            return url;
        }

        System.out.println("SOURCE: DB");

        url = urlRepository.findByShortLink(shortLink)
                .orElse(null);

        if (url == null) return null;

        updateCache(shortLink, url.getOriginalUrl(), url);

        return url;
    }

    // CACHE READ
    private String getFromCache(String shortLink) {
        return redisTemplate.opsForValue().get(shortLink);
    }

    // CACHE WRITE
    private void updateCache(String shortLink, String originalUrl, Url url) {

        if (url.getExpirationDate() != null) {

            long seconds = Duration.between(
                    LocalDateTime.now(),
                    url.getExpirationDate()
            ).getSeconds();

            if (seconds > 0) {
                redisTemplate.opsForValue().set(
                        shortLink,
                        originalUrl,
                        Duration.ofSeconds(seconds)
                );
            }

        } else {
            redisTemplate.opsForValue().set(shortLink, originalUrl);
        }
    }

    // DELETE
    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }

    // GET BY SHORT LINK
    @Override
    public Url getUrlByShortLink(String shortLink) {
        return urlRepository.findByShortLink(shortLink)
                .orElse(null);
    }
}