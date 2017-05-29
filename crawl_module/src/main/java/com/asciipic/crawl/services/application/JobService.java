package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Job;
import com.asciipic.crawl.services.basic.ReadService;
import com.asciipic.crawl.services.basic.UpdateService;


public interface JobService extends ReadService<Job>, UpdateService<Job> {
}
