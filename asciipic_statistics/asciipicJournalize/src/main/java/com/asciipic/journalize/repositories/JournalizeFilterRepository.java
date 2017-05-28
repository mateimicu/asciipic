package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.JournalizeFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeFilterRepository extends JpaRepository<JournalizeFilter, Long> {
}