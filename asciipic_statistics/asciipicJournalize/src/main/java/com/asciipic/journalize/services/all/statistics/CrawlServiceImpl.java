package com.asciipic.journalize.services.all.statistics;

import java.util.List;
import com.asciipic.journalize.models.JournalizeCrawl;
import com.asciipic.journalize.repositories.JournalizeCrawlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CrawlServiceImpl implements CrawlService{

    @Autowired
    private JournalizeCrawlRepository journalizeCrawlRepository;

    @Override
    public List<JournalizeCrawl> getAll() {
        return journalizeCrawlRepository.findAll();
    }
}
