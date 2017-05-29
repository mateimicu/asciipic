package com.asciipic.crawl.dtos;

import java.io.Serializable;
import java.util.List;


public class CrawlContent implements Serializable {
    List<String> urls;

    public CrawlContent() {
    }

    public CrawlContent(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
