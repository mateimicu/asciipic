package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.Job;
import com.asciipic.journalize.models.JournalizeCrawl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}