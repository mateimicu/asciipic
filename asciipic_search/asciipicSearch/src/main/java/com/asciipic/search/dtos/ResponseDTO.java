package com.asciipic.search.dtos;


import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable {

    private Metadata metadata;
    private Content content;

    public ResponseDTO(Boolean status, String verbose, List<String> urls) {
        this.metadata = new Metadata(status, verbose);
        this.content = new Content(urls);
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    private class Content implements Serializable {
        private List<String> urls;

        Content(List<String> urls) {
            this.urls = urls;
        }

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }
    }

    private class Metadata implements Serializable {
        private Boolean status;
        private String verbose;

        Metadata(Boolean status, String verbose) {
            this.status = status;
            this.verbose = verbose;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getVerbose() {
            return verbose;
        }

        public void setVerbose(String verbose) {
            this.verbose = verbose;
        }

    }


}

