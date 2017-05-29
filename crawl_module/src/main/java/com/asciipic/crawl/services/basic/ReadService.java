package com.asciipic.crawl.services.basic;

import java.util.List;

public interface ReadService<T> {
    List<T> getAll();

    T getById(Long id);
}