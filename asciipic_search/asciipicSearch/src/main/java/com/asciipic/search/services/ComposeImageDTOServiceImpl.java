package com.asciipic.search.services;

import com.asciipic.search.dtos.ImagePostDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ComposeImageDTOServiceImpl implements ComposeImageDTOService{

    @Override
    public ImagePostDTO composeImageDTO(MultiValueMap<String, String> requestParams) throws Exception {
        ImagePostDTO informationJSON = new ImagePostDTO();

        for (Map.Entry<String, List<String>> entry : requestParams.entrySet()) {
            if (!isAValidKey(entry.getKey())) {
                throw new Exception("Option " + entry.getKey() + " invalid !");
            }
        }
        informationJSON.setSource(requestParams.get("source") == null ?"" : requestParams.get("source").get(0));
        informationJSON.setPostDate(requestParams.get("postDate") == null ? "" : requestParams.get("postDate").get(0));
        informationJSON.setCrawlDate(requestParams.get("crawlDate") == null ? "" : requestParams.get("crawlDate").get(0));
        informationJSON.setHeight(requestParams.get("height") == null ? "" : requestParams.get("height").get(0));
        informationJSON.setWidth(requestParams.get("width") == null ? "" : requestParams.get("width").get(0));
        informationJSON.setTags(requestParams.get("tag") == null ? new ArrayList<>() : requestParams.get("tag"));

        return informationJSON;
    }

    private boolean isAValidKey(String key) {
        if (key.equals("source") || key.equals("tag") || key.equals("crawlDate") || key.equals("postDate") ||
                key.equals("height") || key.equals("width") ) {
            return true;
        }
        return false;
    }
}
