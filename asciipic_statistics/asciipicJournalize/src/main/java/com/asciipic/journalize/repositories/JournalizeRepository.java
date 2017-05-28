package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.Journalize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeRepository extends JpaRepository<Journalize, Long> {
}