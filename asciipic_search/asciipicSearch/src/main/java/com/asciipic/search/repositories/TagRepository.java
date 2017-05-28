package com.asciipic.search.repositories;


import com.asciipic.search.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Long> {
    //Tag findByName(String name);
}