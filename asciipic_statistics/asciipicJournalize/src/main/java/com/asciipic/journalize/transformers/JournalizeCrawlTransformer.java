package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JournalizeCrawlDTO;
import com.asciipic.journalize.models.Job;
import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeCrawl;
import com.asciipic.journalize.repositories.JobRepository;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalizeCrawlTransformer {
    @Autowired
    private JournalizeRepository journalizeRepository;

    @Autowired
    private JobRepository jobRepository;

    public JournalizeCrawlDTO toDTO(JournalizeCrawl journalizeCrawl){
        JournalizeCrawlDTO journalizeCrawlDTO = new JournalizeCrawlDTO();

        Journalize journalize = journalizeRepository.getOne(journalizeCrawl.getId());

        UserTransformer userTransformer = new UserTransformer();
        JobTransformer jobTransformer = new JobTransformer();

        Job job = jobRepository.findOne(journalizeCrawl.getJopId());

        journalizeCrawlDTO.setActionType(journalize.getAction());
        journalizeCrawlDTO.setActionDate(journalize.getActionDate());
        journalizeCrawlDTO.setUserDetails(userTransformer.toDTO(journalizeCrawl.getUser()));
        journalizeCrawlDTO.setJobDetails(jobTransformer.toDTO(job));

        return journalizeCrawlDTO;
    }
}
