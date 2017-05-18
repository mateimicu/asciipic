package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JournalizeSearchDTO;
import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeSearch;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalizeSearchTransformer {
    @Autowired
    private JournalizeRepository journalizeRepository;

    public JournalizeSearchDTO toDTO(JournalizeSearch journalizeSearch){
        JournalizeSearchDTO journalizeSearchDTO = new JournalizeSearchDTO();

        Journalize journalize = journalizeRepository.getOne(journalizeSearch.getId());

        UserTransformer userTransformer = new UserTransformer();

        journalizeSearchDTO.setActionType(journalize.getAction());
        journalizeSearchDTO.setActionDate(journalize.getActionDate());
        journalizeSearchDTO.setUserDetails(userTransformer.toDTO(journalizeSearch.getUser()));
        journalizeSearchDTO.setTag(journalizeSearch.getTag());
        journalizeSearchDTO.setPostDate(journalizeSearch.getPostDate());
        journalizeSearchDTO.setHeight(journalizeSearch.getHeight());
        journalizeSearchDTO.setWidth(journalizeSearch.getWidth());

        return journalizeSearchDTO;
    }
}
