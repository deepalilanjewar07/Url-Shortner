package com.example.urlshortner.kafka;

import com.example.urlshortner.kafka.event.ClickEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClickEventProducer {

    private static final Logger log =
            LoggerFactory.getLogger(ClickEventProducer.class);

    @Autowired
    private KafkaTemplate<String, ClickEvent> kafkaTemplate;

    public void sendClickEvent(ClickEvent event) {
        log.info("Sending event to Kafka: {}", event.getShortLink());
        kafkaTemplate.send(KafkaTopics.CLICK_TOPIC, event);


    }
}