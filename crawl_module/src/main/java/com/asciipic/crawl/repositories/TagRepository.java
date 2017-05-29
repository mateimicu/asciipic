package com.asciipic.crawl.repositories;

import com.asciipic.crawl.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}