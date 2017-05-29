package com.asciipic.crawl.controllers;

import com.asciipic.crawl.dtos.CrawlResponseDTO;
import com.asciipic.crawl.dtos.CrawlUpdateDTO;
import com.asciipic.crawl.job.RedisJobPoster;
import com.asciipic.crawl.models.Crawl;
import com.asciipic.crawl.models.Job;
import com.asciipic.crawl.models.Tag;
import com.asciipic.crawl.models.Url;
import com.asciipic.crawl.services.application.CrawlService;
import com.asciipic.crawl.services.application.JobService;
import com.asciipic.crawl.services.application.TagService;
import com.asciipic.crawl.services.application.UrlService;
import com.asciipic.crawl.transformers.CrawlToCrawlResponseDTO;
import com.asciipic.crawl.transformers.CrawlToJobPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/crawls")
public class CrawlController {
    private final java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd-mm-yyyy");

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private JobService jobService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private CrawlToCrawlResponseDTO crawlToCrawlResponseDTO;

    @Autowired
    private CrawlToJobPostDTO crawlToJobPostDTO;

    @Autowired
    private RedisJobPoster redisJobPoster;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CrawlResponseDTO>> get() {
        List<CrawlResponseDTO> crawlResponseDTOs = new ArrayList<>();
        for(Crawl crawl:crawlService.getAll()){
            crawlResponseDTOs.add(crawlToCrawlResponseDTO.transform(crawl));
        }
        return new ResponseEntity<>(crawlResponseDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{crawl_id}", method = RequestMethod.GET)
    public ResponseEntity<CrawlResponseDTO> getById(@PathVariable(value = "crawl_id") long id) {
        return new ResponseEntity<>(crawlToCrawlResponseDTO.transform(crawlService.getById(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CrawlResponseDTO> post(@RequestParam(value = "site") String site,
                                                 @RequestParam(value = "tag") String[] tagNames,
                                                 @RequestParam(value = "number", required = false) Long numberOfImages,
                                                 @RequestParam(value = "width", required = false) Long width,
                                                 @RequestParam(value = "height", required = false) Long height,
                                                 @RequestParam(value = "post_date", required = false) String dateString) {
        Date date = null;
        if (dateString != null) {
            try {
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Wrong date format!");
            }

        }

        Job job = this.jobService.save(new Job());
        List<Tag> tags = getTagList(tagNames);
        Crawl crawl = new Crawl(job, false, new ArrayList<>(), site, date,
                height == null ? 0 : height.intValue(), width == null ? 0 : width.intValue(),
                numberOfImages == null ? 1 : numberOfImages.intValue(), tags);
        crawl = crawlService.save(crawl);
        redisJobPoster.post(crawlToJobPostDTO.transform(crawl));
        return new ResponseEntity<>(crawlToCrawlResponseDTO.transform(crawl), HttpStatus.OK);
    }


    @RequestMapping(value = "/{crawl_id}", method = RequestMethod.POST)
    public ResponseEntity postWorkerUpdate(@PathVariable(value = "crawl_id") Long id, @RequestBody CrawlUpdateDTO crawlUpdateDTO) {
        Job job = crawlService.getById(id).getJob();
        if (crawlUpdateDTO.getLinkNames().isEmpty() && job.getStartDate() == null) {
            job.setStartDate(new Date());
            jobService.save(job);
        } else {
            List<Url> urls = new ArrayList<>();
            for (String linkName : crawlUpdateDTO.getLinkNames()) {
                urls.add(urlService.save(new Url(linkName)));
            }
            Crawl crawl = crawlService.getById(id);
            crawl.setUrls(urls);
            crawl.setDone(true);
            crawlService.save(crawl);


            job.setFinishDate(new Date());
            jobService.save(job);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    private List<Tag> getTagList(String[] tagNames) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = new Tag(tagName);
            tag = this.tagService.save(tag);
            tags.add(tag);
        }
        return tags;
    }
}
