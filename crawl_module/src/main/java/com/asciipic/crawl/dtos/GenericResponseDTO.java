package com.asciipic.crawl.dtos;

import java.io.Serializable;

public class GenericResponseDTO<M, C> implements Serializable {

    private M metadata;
    private C content;

    public M getMetadata() {
        return metadata;
    }

    public void setMetadata(M metadata) {
        this.metadata = metadata;
    }

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }
}
