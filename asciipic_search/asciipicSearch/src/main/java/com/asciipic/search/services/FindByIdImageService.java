package com.asciipic.search.services;

import com.asciipic.search.models.SavedImage;

public interface FindByIdImageService {
    SavedImage findImageById(Long imageId);
}
