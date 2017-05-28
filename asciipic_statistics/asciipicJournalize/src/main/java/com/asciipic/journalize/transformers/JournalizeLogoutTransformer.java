package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JournalizeLogoutDTO;
import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeLogout;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalizeLogoutTransformer {
    @Autowired
    private JournalizeRepository journalizeRepository;

    public JournalizeLogoutDTO toDTO(JournalizeLogout journalizeLogout) {
        JournalizeLogoutDTO journalizeLogoutDTO = new JournalizeLogoutDTO();

        Journalize journalize = journalizeRepository.getOne(journalizeLogout.getId());

        UserTransformer userTransformer = new UserTransformer();

        journalizeLogoutDTO.setActionType(journalize.getAction());
        journalizeLogoutDTO.setActionDate(journalize.getActionDate());
        journalizeLogoutDTO.setUserDetails(userTransformer.toDTO(journalizeLogout.getUser()));
        journalizeLogoutDTO.setCause(journalizeLogout.getCause());

        return journalizeLogoutDTO;
    }
}
