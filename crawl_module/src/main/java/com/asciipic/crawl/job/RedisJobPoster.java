package com.asciipic.crawl.job;

import com.asciipic.crawl.dtos.JobPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisJobPoster implements JobPoster {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    public RedisJobPoster() {
    }

    public RedisJobPoster(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void post(final JobPostDTO job) {
        redisTemplate.convertAndSend(topic.getTopic(), job);
    }
}