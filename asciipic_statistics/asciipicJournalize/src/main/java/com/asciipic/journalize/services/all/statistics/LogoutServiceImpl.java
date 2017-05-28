package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.JournalizeLogout;
import com.asciipic.journalize.repositories.JournalizeLogoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogoutServiceImpl implements LogoutService{

    @Autowired
    private JournalizeLogoutRepository journalizeLogoutRepository;

    @Override
    public List<JournalizeLogout> getAll() {
        return journalizeLogoutRepository.findAll();
    }
}
