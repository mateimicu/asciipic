package com.asciipic.crawl.dtos;

import java.io.Serializable;


public class CrawlResponseDTO extends GenericResponseDTO<CrawlMetadata, CrawlContent> implements Serializable{
    public CrawlResponseDTO(){
        this.setMetadata(new CrawlMetadata());
        this.setContent(new CrawlContent());
    }
}


