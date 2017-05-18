package com.asciipic.journalize.services.all.statistics;

import com.asciipic.journalize.models.Job;
import com.asciipic.journalize.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }
}
