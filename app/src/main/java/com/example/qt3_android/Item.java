package com.example.qt3_android;

public class Item {
    private String title;
    private String description;
    private String publicDate;
    private String mediaUrl;
    private String mediaDescription;
    private String link;

    public Item(String title, String description, String publicDate, String mediaUrl, String mediaDescription, String link) {
        this.title = title;
        this.description = description;
        this.publicDate = publicDate;
        this.mediaUrl = mediaUrl;
        this.mediaDescription = mediaDescription;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaDescription() {
        return mediaDescription;
    }

    public void setMediaDescription(String mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
