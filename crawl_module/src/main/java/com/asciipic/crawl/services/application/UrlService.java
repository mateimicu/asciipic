package com.asciipic.crawl.services.application;

import com.asciipic.crawl.models.Url;
import com.asciipic.crawl.services.basic.ReadService;
import com.asciipic.crawl.services.basic.UpdateService;


public interface UrlService extends ReadService<Url>, UpdateService<Url> {
    Url getByName(String name);
}
