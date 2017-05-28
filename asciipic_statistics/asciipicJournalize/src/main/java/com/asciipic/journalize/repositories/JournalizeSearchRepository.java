package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.JournalizeSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeSearchRepository extends JpaRepository<JournalizeSearch, Long> {
}