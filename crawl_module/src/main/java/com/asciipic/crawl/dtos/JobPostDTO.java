package com.asciipic.crawl.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class JobPostDTO implements Serializable {

    private long crawlId;
    private String site;
    private long number;
    private List<String> tags;
    private Date postDate;
    private long width;
    private long height;

    public JobPostDTO() {
    }

    public long getCrawlId() {
        return crawlId;
    }

    public void setCrawlId(long crawlId) {
        this.crawlId = crawlId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
