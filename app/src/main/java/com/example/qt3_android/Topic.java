package com.example.qt3_android;

import java.net.URLEncoder;
import java.util.Locale;

public class Topic {
    private int id;
    private String name;
    private String url;

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
        String url = id + "-" + name;
        url = url.replaceAll(" ", "-");
        this.url = url.toLowerCase(Locale.ROOT);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
