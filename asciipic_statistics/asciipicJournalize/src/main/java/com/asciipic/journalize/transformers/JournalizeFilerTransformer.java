package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JournalizeFilterDTO;
import com.asciipic.journalize.models.Image;
import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeFilter;
import com.asciipic.journalize.repositories.ImageRepository;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalizeFilerTransformer {

    @Autowired
    private JournalizeRepository journalizeRepository;

    @Autowired
    private ImageRepository imageRepository;

    public JournalizeFilterDTO toDTO(JournalizeFilter journalizeFilter){
        JournalizeFilterDTO journalizeFilterDTO = new JournalizeFilterDTO();

        Journalize journalize = journalizeRepository.getOne(journalizeFilter.getId());

        UserTransformer userTransformer = new UserTransformer();

        Image image = imageRepository.findOne(journalizeFilter.getImageId());

        journalizeFilterDTO.setActionType(journalize.getAction());
        journalizeFilterDTO.setActionDate(journalize.getActionDate());
        journalizeFilterDTO.setUserDetails(userTransformer.toDTO(journalizeFilter.getUser()));
        journalizeFilterDTO.setImagePostDate(image.getPostDate());
        journalizeFilterDTO.setHeight(image.getHeight());
        journalizeFilterDTO.setWidth(image.getWidth());
        journalizeFilterDTO.setTypeFilter(journalizeFilter.getType());

        return journalizeFilterDTO;
    }
}
