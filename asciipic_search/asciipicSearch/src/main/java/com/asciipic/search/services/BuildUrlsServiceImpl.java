package com.asciipic.search.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildUrlsServiceImpl implements BuildUrlsService {
    private String path = "http://localhost:8085/images/";

    public List<String> buildUrls(List<Long> ids) {

        List<String> urlsList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            urlsList.add(path + ids.get(i).toString());
        }
        return urlsList;
    }
}
