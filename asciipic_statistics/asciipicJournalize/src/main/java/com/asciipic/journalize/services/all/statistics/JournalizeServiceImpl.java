package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalizeServiceImpl implements JournalizeService {

    @Autowired
    private JournalizeRepository journalizeRepository;
    @Override
    public List<Journalize> getAll() {
        Journalize journalize = new Journalize("action");
        journalizeRepository.save(journalize);
        return journalizeRepository.findAll();
    }
}
