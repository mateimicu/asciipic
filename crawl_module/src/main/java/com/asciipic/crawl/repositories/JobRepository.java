package com.asciipic.crawl.repositories;

import com.asciipic.crawl.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long> {
}