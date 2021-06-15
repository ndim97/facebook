package com.example.facebook.dto;

import com.example.facebook.entity.Image;

public class ImageDTO extends Image {

    private String url;

    public ImageDTO() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
