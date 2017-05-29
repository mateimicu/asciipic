package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Url;
import com.asciipic.crawl.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository repository;

    @Override
    public List<Url> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Url getById(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public Url save(Url entity) {
        Url url = getByName(entity.getName());
        if(url != null){
            return url;
        }
        return this.repository.save(entity);
    }

    @Override
    public Url getByName(String name) {
        return repository.findByName(name);
    }
}
