package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.JournalizeRegister;
import com.asciipic.journalize.repositories.JournalizeRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    private JournalizeRegisterRepository journalizeRegisterRepository;

    @Override
    public List<JournalizeRegister> getAll() {
        return journalizeRegisterRepository.findAll();
    }
}
