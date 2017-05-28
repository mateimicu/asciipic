package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}