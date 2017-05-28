package com.asciipic.search.repositories;

import com.asciipic.search.models.SavedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedImageRepository extends JpaRepository<SavedImage, Long> {
}
