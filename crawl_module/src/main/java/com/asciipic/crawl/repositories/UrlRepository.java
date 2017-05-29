package com.asciipic.crawl.repositories;

import com.asciipic.crawl.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByName(String name);
}