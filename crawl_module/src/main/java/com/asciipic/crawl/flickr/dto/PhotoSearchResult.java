package com.asciipic.crawl.flickr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoSearchResult implements Serializable {
    private Photos photos;
    private String stat;

    public PhotoSearchResult() {
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "PhotoSearchResult{" +
                "photos=" + photos +
                ", stat='" + stat + '\'' +
                '}';
    }
}
