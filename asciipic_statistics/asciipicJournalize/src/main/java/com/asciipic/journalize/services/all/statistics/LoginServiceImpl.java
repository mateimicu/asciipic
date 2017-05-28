package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.JournalizeLogin;
import com.asciipic.journalize.repositories.JournalizeLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private JournalizeLoginRepository journalizeLoginRepository;

    @Override
    public List<JournalizeLogin> getAll() {
        return journalizeLoginRepository.findAll();
    }


}
