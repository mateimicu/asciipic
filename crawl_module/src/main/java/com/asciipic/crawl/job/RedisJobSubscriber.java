package com.asciipic.crawl.job;


import com.asciipic.crawl.dtos.CrawlUpdateDTO;
import com.asciipic.crawl.dtos.JobPostDTO;
import com.asciipic.crawl.flickr.FlickrAPI;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class RedisJobSubscriber implements MessageListener {

    public void onMessage(final Message message, final byte[] pattern) {
        JobPostDTO job = new Jackson2JsonRedisSerializer<>(JobPostDTO.class).deserialize(message.getBody());
        String url = "http://localhost:9669/crawls/" + job.getCrawlId();


        new RestTemplate().postForObject(url, new CrawlUpdateDTO(new ArrayList<String>()), String.class);
        System.out.println("Entered in job processor and processing crawl number " + job.getCrawlId() + ".");
        try {
            Thread.sleep(20000);
            new RestTemplate().postForObject(url, new CrawlUpdateDTO(new FlickrAPI(job).getResultLinks()), String.class);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Crawl number " + job.getCrawlId() + " was successfully completed.");
    }
}