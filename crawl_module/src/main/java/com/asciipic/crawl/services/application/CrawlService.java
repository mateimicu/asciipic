package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Crawl;
import com.asciipic.crawl.services.basic.ReadService;
import com.asciipic.crawl.services.basic.UpdateService;


public interface CrawlService extends ReadService<Crawl>, UpdateService<Crawl> {
}
