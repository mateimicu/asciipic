package com.asciipic.crawl.flickr;

import com.asciipic.crawl.dtos.JobPostDTO;
import com.asciipic.crawl.flickr.dto.Photo;
import com.asciipic.crawl.flickr.dto.PhotoSearchResult;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FlickrAPI {
    private final String linkHeader = "https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&" +
            "nojsoncallback=1&api_key=2c584c0a6b21310581ab99a610b2e5bd&tag_mode=all&media=photos&privacy_filter=public" +
            "&extras=url_c,url_l,url_o";
    private final java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd-mm-yyyy");


    private JobPostDTO job;
    private PhotoSearchResult photoSearchResult;
    private List<String> resultLinks;

    public FlickrAPI(JobPostDTO job) {
        this.job = job;
        resultLinks = null;
    }

    public List<String> getResultLinks() {
        photoSearchResult = new RestTemplate().getForObject(getInterrogationLink(), PhotoSearchResult.class);

        if (photoSearchResult.getStat().matches("ok")) {
            resultLinks = new ArrayList<>();
        }
        for (Photo photo : photoSearchResult.getPhotos().getPhoto()) {
            if (resultLinks.size() == job.getNumber()) {
                break;
            } else {
                if (photo.getUrl_o() != null) {
                    resultLinks.add(photo.getUrl_o());
                }
            }
        }
        // System.out.println(resultLinks);
        return resultLinks;
    }

    private String getInterrogationLink() {
        StringBuilder link = new StringBuilder(linkHeader);
        if (!job.getTags().isEmpty()) {
            link.append("&tags=");
        }
        for (String tag : job.getTags()) {
            link.append(tag).append(",");
        }
        link.delete(link.length() - 4, link.length());
        link.append("&per_page=").append(20);
        if (job.getPostDate() != null) {
            link.append("&min_upload_date=").append((job.getPostDate().getTime()) / 1000);
            link.append("&max_upload_date=").append(job.getPostDate().getTime() / 1000 + 86399);
        }

        System.out.println(link.toString());
        return link.toString();
    }
}
