package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.JournalizeLogout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeLogoutRepository extends JpaRepository<JournalizeLogout, Long> {
}