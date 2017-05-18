package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JournalizeLoginDTO;
import com.asciipic.journalize.dtos.UserDTO;
import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeLogin;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JournalizeLoginTransformer {
    @Autowired
    private JournalizeRepository journalizeRepository;


    public JournalizeLoginDTO toDTO(JournalizeLogin journalizeLogin){
        JournalizeLoginDTO journalizeLoginDTO = new JournalizeLoginDTO();

        Journalize journalize = journalizeRepository.getOne(journalizeLogin.getId());

        UserTransformer userTransformer = new UserTransformer();
        UserDTO userDTO = userTransformer.toDTO(journalizeLogin.getUser());

        journalizeLoginDTO.setActionType(journalize.getAction());
        journalizeLoginDTO.setActionDate(journalize.getActionDate());
        journalizeLoginDTO.setUserDetails(userDTO);
        journalizeLoginDTO.setIp(journalizeLogin.getIp());
        journalizeLoginDTO.setUserAgent(journalizeLogin.getUserAgent());

        return journalizeLoginDTO;
    }
}
