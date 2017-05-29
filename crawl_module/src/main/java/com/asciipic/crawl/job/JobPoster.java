package com.asciipic.crawl.job;

import com.asciipic.crawl.dtos.JobPostDTO;

public interface JobPoster {
    void post(final JobPostDTO job);
}