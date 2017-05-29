package com.asciipic.crawl.repositories;

import com.asciipic.crawl.models.Crawl;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CrawlRepository extends JpaRepository<Crawl, Long> {
}