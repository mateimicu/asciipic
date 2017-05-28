package com.asciipic.search.services;

import com.asciipic.search.dtos.ImagePostDTO;

import java.util.List;

public interface GetCustomImagesService {
    List<Long> getAllImages(ImagePostDTO informationJSON);
}

