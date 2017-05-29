package com.asciipic.crawl.services.application;

        import com.asciipic.crawl.models.Job;
        import com.asciipic.crawl.repositories.JobRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;


@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository repository;

    @Override
    public List<Job> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Job getById(Long id) {
        return this.repository.findOne(id);
    }

    @Override
    public Job save(Job entity) {
        return this.repository.save(entity);
    }
}
