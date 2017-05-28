package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.JournalizeFilter;
import com.asciipic.journalize.repositories.JournalizeFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    private JournalizeFilterRepository journalizeFilterRepository;

    @Override
    public List<JournalizeFilter> getAll() {
        return journalizeFilterRepository.findAll();
    }
}
