package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Tag;
import com.asciipic.crawl.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository repository;

    @Override
    public List<Tag> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Tag getById(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public Tag save(Tag entity) {
        Tag tag = getByName(entity.getName());
        if(tag != null){
            return tag;
        }
        return this.repository.save(entity);
    }

    @Override
    public Tag getByName(String name){
        return this.repository.findByName(name);
    }
}
