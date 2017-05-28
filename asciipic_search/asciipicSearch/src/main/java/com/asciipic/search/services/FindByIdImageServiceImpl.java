package com.asciipic.search.services;

import com.asciipic.search.models.SavedImage;
import com.asciipic.search.repositories.SavedImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdImageServiceImpl implements FindByIdImageService {

    @Autowired
    SavedImageRepository savedImageRepository;

    @Override
    public SavedImage findImageById(Long imageId) {

        return savedImageRepository.findOne(imageId);
    }
}
