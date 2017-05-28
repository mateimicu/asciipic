package com.asciipic.search.services;

import com.asciipic.search.dtos.ImagePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class BuildResponseServiceImpl implements BuildResponseService {
    @Autowired
    ComposeImageDTOService composeImageDTOService;

    @Autowired
    GetCustomImagesService getCustomImagesService;

    @Autowired
    BuildUrlsService buildUrlsService;

    public List<String> getResponse(MultiValueMap<String, String> requestParams) throws Exception {
        ImagePostDTO imagePostDTO = composeImageDTOService.composeImageDTO(requestParams);
        List<Long> ids = getCustomImagesService.getAllImages(imagePostDTO);
        List<String> urlList = buildUrlsService.buildUrls(ids);

        if (urlList.isEmpty()) {
            throw new Exception("There is no image with this properties!");
        }
        return urlList;
    }
}
