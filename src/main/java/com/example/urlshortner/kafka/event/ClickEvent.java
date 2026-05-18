package com.example.urlshortner.kafka.event;

public class ClickEvent {

    private String shortLink;
    private String ip;
    private long timestamp;

    public ClickEvent() {}

    public ClickEvent(String shortLink, String ip, long timestamp) {
        this.shortLink = shortLink;
        this.ip = ip;
        this.timestamp = timestamp;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}