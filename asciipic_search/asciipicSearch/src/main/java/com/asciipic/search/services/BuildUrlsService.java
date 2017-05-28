package com.asciipic.search.services;

import java.util.List;

public interface BuildUrlsService {
    List<String> buildUrls(List<Long> ids);
}
