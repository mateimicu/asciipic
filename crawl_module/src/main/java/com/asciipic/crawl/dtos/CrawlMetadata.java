package com.asciipic.crawl.dtos;

import java.io.Serializable;


public class CrawlMetadata implements Serializable {
    private Boolean status;
    private String verbose;

    public CrawlMetadata() {
    }

    public CrawlMetadata(Boolean status, String verbose) {
        this.status = status;
        this.verbose = verbose;
    }

    public Boolean getDone() {
        return status;
    }

    public void setDone(Boolean done) {
        status = done;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }
}
