package com.asciipic.search.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/images")
public class ImageController {

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public ResponseEntity<String> getImageById(@PathVariable("imageId") Long imageId) {

        System.out.println(imageId);
        return new ResponseEntity(HttpStatus.OK);
    }

}

