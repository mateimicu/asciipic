package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Crawl;
import com.asciipic.crawl.repositories.CrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CrawlServiceImpl implements CrawlService {
    @Autowired
    private CrawlRepository repository;

    @Override
    public List<Crawl> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Crawl getById(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public Crawl save(Crawl entity) {
        return this.repository.save(entity);
    }
}
