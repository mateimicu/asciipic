package com.asciipic.crawl.controllers;

import com.asciipic.crawl.models.Url;
import com.asciipic.crawl.services.application.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {
    @Autowired
    UrlService urlService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Url> post(@RequestBody String name) {
        return new ResponseEntity<>(urlService.save(new Url(name)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Url>> get() {
        return new ResponseEntity<>(urlService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value= "/name/{urlName}", method = RequestMethod.GET)
    public ResponseEntity<Url> getByName(@RequestParam(name = "urlName") String urlName) {
        return new ResponseEntity<>(urlService.getByName(urlName), HttpStatus.OK);
    }

    @RequestMapping(value="/{urlId}", method = RequestMethod.GET)
    public ResponseEntity<Url> getByTagId(@RequestParam(name = "urlId") long urlId) {
        return new ResponseEntity<>(urlService.getById(urlId), HttpStatus.OK);
    }
}
