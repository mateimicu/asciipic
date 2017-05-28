package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.JournalizeSearch;
import com.asciipic.journalize.repositories.JournalizeSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private JournalizeSearchRepository journalizeSearchRepository;

    @Override
    public List<JournalizeSearch> getAll() {
        return journalizeSearchRepository.findAll();
    }
}
