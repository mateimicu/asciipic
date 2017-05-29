package com.asciipic.crawl.controllers;

import com.asciipic.crawl.models.Tag;
import com.asciipic.crawl.services.application.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Tag> post(@RequestBody String name) {
        return new ResponseEntity<>(tagService.save(new Tag(name)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tag>> get() {
        return new ResponseEntity<>(tagService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/name/{tagName}", method = RequestMethod.GET)
    public ResponseEntity<Tag> getByName(@RequestParam(name = "tagName") String tagName) {
        return new ResponseEntity<>(tagService.getByName(tagName), HttpStatus.OK);
    }

    @RequestMapping(value="/{tagId}", method = RequestMethod.GET)
    public ResponseEntity<Tag> getByTagId(@RequestParam(name = "tagId") long tagId) {
        return new ResponseEntity<>(tagService.getById(tagId), HttpStatus.OK);
    }
}
