package com.asciipic.crawl.flickr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo implements Serializable {
    private String url_o;
    private String url_c;
    private String url_l;

    public Photo() {
    }

    public String getUrl_o() {
        return url_o;
    }

    public void setUrl_o(String url_o) {
        this.url_o = url_o;
    }

    public String getUrl_c() {
        return url_c;
    }

    public void setUrl_c(String url_c) {
        this.url_c = url_c;
    }

    public String getUrl_l() {
        return url_l;
    }

    public void setUrl_l(String url_l) {
        this.url_l = url_l;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "url_o='" + url_o + '\'' +
                ", url_c='" + url_c + '\'' +
                ", url_l='" + url_l + '\'' +
                '}';
    }
}