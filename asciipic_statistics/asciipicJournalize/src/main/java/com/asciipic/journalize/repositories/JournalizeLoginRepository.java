package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.JournalizeLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeLoginRepository extends JpaRepository<JournalizeLogin, Long> {
}