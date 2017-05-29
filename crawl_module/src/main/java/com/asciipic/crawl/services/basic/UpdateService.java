package com.asciipic.crawl.services.basic;

public interface UpdateService<T> {
    T save(T entity);
}