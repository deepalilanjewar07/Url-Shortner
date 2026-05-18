package com.example.urlshortner.kafka;

import com.example.urlshortner.kafka.event.ClickEvent;
import com.example.urlshortner.model.Url;
import com.example.urlshortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClickEventConsumer {

    @Autowired
    private UrlRepository urlRepository;

    private static final Logger log =
            LoggerFactory.getLogger(ClickEventConsumer.class);

    @KafkaListener(topics = KafkaTopics.CLICK_TOPIC, groupId = "url-shortner-group")
    public void consumeEvent(ClickEvent event) {

        try {

            log.info("Kafka event received for shortLink: {}", event.getShortLink());

            Url url = urlRepository.findByShortLink(event.getShortLink())
                    .orElse(null);
            if (url != null) {
                url.setClickCount(url.getClickCount() + 1);
                url.setLastAccessed(LocalDateTime.now());

                urlRepository.save(url);

                log.info("DB updated for shortLink: {}", event.getShortLink());
            }

        } catch (Exception e) {
            log.error("Error processing Kafka event", e);
        }
    }
}