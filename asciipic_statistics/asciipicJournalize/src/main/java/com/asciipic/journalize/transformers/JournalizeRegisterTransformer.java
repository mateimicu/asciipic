package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JournalizeRegisterDTO;
import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeRegister;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalizeRegisterTransformer {
    @Autowired
    private JournalizeRepository journalizeRepository;

    public JournalizeRegisterDTO toDTO(JournalizeRegister journalizeRegister) {
        JournalizeRegisterDTO journalizeRegisterDTO = new JournalizeRegisterDTO();

        Journalize journalize = journalizeRepository.getOne(journalizeRegister.getId());

        UserTransformer userTransformer = new UserTransformer();

        journalizeRegisterDTO.setActionType(journalize.getAction());
        journalizeRegisterDTO.setActionDate(journalize.getActionDate());
        journalizeRegisterDTO.setUserDetails(userTransformer.toDTO(journalizeRegister.getUser()));
        journalizeRegisterDTO.setIp(journalizeRegister.getIp());

        return journalizeRegisterDTO;
    }
}
