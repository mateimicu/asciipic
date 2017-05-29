package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Tag;
import com.asciipic.crawl.services.basic.ReadService;
import com.asciipic.crawl.services.basic.UpdateService;


public interface TagService extends ReadService<Tag>, UpdateService<Tag> {
    Tag getByName(String name);
}
