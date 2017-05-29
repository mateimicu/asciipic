package com.asciipic.crawl.transformers;

import com.asciipic.crawl.dtos.JobPostDTO;
import com.asciipic.crawl.models.Crawl;
import com.asciipic.crawl.models.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CrawlToJobPostDTO implements Transformer<Crawl, JobPostDTO> {

    @Override
    public JobPostDTO transform(Crawl entity) {
        JobPostDTO jobPostDTO = new JobPostDTO();
        jobPostDTO.setCrawlId(entity.getId());
        jobPostDTO.setSite(entity.getSite());
        jobPostDTO.setNumber(entity.getNumberOfImages());
        jobPostDTO.setTags(tagListToTagNamesList(entity.getTags()));
        jobPostDTO.setPostDate(entity.getPostDate());
        jobPostDTO.setWidth(entity.getWidth());
        jobPostDTO.setHeight(entity.getHeight());
        return jobPostDTO;
    }

    private List<String> tagListToTagNamesList(List<Tag> tags) {
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : tags) {
            tagNames.add(tag.getName());
        }
        return tagNames;
    }
}
