package com.asciipic.search.controllers;

import com.asciipic.search.models.SavedImage;
import com.asciipic.search.services.FindByIdImageService;
import com.sun.javafx.iio.ImageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;


@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    FindByIdImageService findByIdImageService;


    @RequestMapping(value = "/{imageId}",produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageById(@PathVariable("imageId") Long imageId) throws ParseException, SQLException {
        SavedImage savedImage = findByIdImageService.findImageById(imageId);

        if(savedImage == null){
            return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
        }

        Blob blob =  savedImage.getData();
        int blobLength = (int)  blob.length();
        byte[] blobAsBytes = blob.getBytes(1, blobLength);
        blob.free();

        return new ResponseEntity(blobAsBytes,HttpStatus.OK);
    }

}

