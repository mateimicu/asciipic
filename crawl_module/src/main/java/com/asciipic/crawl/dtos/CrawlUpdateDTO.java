package com.asciipic.crawl.dtos;

import java.util.List;

public class CrawlUpdateDTO {
    List<String> linkNames;

    public CrawlUpdateDTO() {
    }

    public CrawlUpdateDTO(List<String> linkNames) {
        this.linkNames = linkNames;
    }

    public List<String> getLinkNames() {
        return linkNames;
    }

    public void setLinkNames(List<String> linkNames) {
        this.linkNames = linkNames;
    }
}
