package com.asciipic.journalize.repositories;

import com.asciipic.journalize.models.Image;
import com.asciipic.journalize.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
