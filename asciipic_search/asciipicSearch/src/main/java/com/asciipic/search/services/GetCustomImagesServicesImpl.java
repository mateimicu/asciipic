package com.asciipic.search.services;

import com.asciipic.search.dtos.ImagePostDTO;
import com.asciipic.search.models.Image;
import com.asciipic.search.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GetCustomImagesServicesImpl implements GetCustomImagesService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Long> getAllImages(ImagePostDTO imagePostDTO) {

        List<Long> listOfIds = new ArrayList<>();
        List<Image> images = imageRepository.findAll();
        for (Image image : images) {
            try {
                if (isAValidImage(image, imagePostDTO)) {
                    listOfIds.add(image.getId());
                }
            } catch (ParseException e) {
                return listOfIds;
            }
        }
        return listOfIds;
    }

    private boolean isAValidImage(Image image, ImagePostDTO imagePostDTO) throws ParseException {
        if (!image.getIsSaved()) {
            return false;
        }
        if (!imagePostDTO.getSource().equals("")) {
            if (!imagePostDTO.getSource().equals(image.getSource())) {
                return false;
            }
        }

        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date dateOne = format.parse(imagePostDTO.getPostDate());
        if (!imagePostDTO.getPostDate().equals("")) {
            if (!dateOne.equals(image.getPostDate())) {
                return false;
            }
        }

        dateOne = format.parse(imagePostDTO.getCrawlDate());
        if (!imagePostDTO.getCrawlDate().equals("")) {
            if (!dateOne.equals(image.getCrawlDate())) {
                return false;
            }
        }
        if (!imagePostDTO.getHeight().equals("")) {
            if (!imagePostDTO.getHeight().equals(Long.toString(image.getHeight()))) {
                return false;
            }
        }
        if (!imagePostDTO.getWidth().equals("")) {
            if (!imagePostDTO.getWidth().equals(Long.toString(image.getWidth()))) {
                return false;
            }
        }

        int ok;
        if (!imagePostDTO.getTags().isEmpty()) {
            for (int i = 0; i < imagePostDTO.getTags().size(); i++) {
                ok = 0;
                for (int j = 0; j < image.getTags().size(); j++) {
                    if (image.getTags().get(j).getName().equals(imagePostDTO.getTags().get(i))) {
                        ok = 1;
                    }
                }
                if (ok == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
