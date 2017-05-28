package com.asciipic.journalize.transformers;

import com.asciipic.journalize.dtos.JobDTO;
import com.asciipic.journalize.models.Job;

public class JobTransformer {
    public JobDTO toDTO(Job job){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setStartDate(job.getStartDate());
        jobDTO.setFinishDate(job.getFinishDate());
        return jobDTO;
    }
    //to model daca trebuie
}
