package com.asciipic.search.services;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface BuildResponseService {
    List<String> getResponse(MultiValueMap<String, String> requestParams) throws Exception;
}
