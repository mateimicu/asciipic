package com.asciipic.crawl.transformers;


import com.asciipic.crawl.dtos.CrawlResponseDTO;
import com.asciipic.crawl.models.Crawl;
import com.asciipic.crawl.models.Url;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Component
public class CrawlToCrawlResponseDTO implements Transformer<Crawl, CrawlResponseDTO> {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String IN_QUEUE = "The job with id %s posted on %s was not yet taken by a worker.";
    private static final String IN_PROGRESS = "The job with id %s was taken by a worker at %s.";
    private static final String IS_DONE = "The job with id %s was finished by a worker at %s.";

    @Override
    public CrawlResponseDTO transform(Crawl entity) {
        CrawlResponseDTO responseDTO = new CrawlResponseDTO();
        responseDTO.getMetadata().setDone(entity.isDone());
        if (entity.isDone()) {
            responseDTO.getMetadata().setVerbose(String.format(IS_DONE, entity.getId(), dateFormat.format((entity.getJob().getFinishDate()))));
            List<String> urlNames = new ArrayList<>();
            for (Url url : entity.getUrls()) {
                urlNames.add(url.getName());
            }
            responseDTO.getContent().setUrls(urlNames);
        } else {
            responseDTO.setContent(null);
            if (entity.getJob().getStartDate() == null) {
                responseDTO.getMetadata().setVerbose(String.format(IN_QUEUE, entity.getId(), dateFormat.format((entity.getJob().getPostDate()))));
            } else {
                responseDTO.getMetadata().setVerbose(String.format(IN_PROGRESS, entity.getId(), dateFormat.format((entity.getJob().getStartDate()))));
            }
        }
        return responseDTO;
    }
}
