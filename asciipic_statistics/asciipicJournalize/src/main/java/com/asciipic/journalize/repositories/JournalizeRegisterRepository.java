package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.JournalizeRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalizeRegisterRepository extends JpaRepository<JournalizeRegister, Long> {
}