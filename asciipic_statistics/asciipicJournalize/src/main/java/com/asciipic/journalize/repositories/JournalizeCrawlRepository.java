package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.JournalizeCrawl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeCrawlRepository extends JpaRepository<JournalizeCrawl, Long> {
}