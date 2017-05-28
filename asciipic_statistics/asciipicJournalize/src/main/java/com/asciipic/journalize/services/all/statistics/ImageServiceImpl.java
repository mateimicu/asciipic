package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.Image;
import com.asciipic.journalize.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}
