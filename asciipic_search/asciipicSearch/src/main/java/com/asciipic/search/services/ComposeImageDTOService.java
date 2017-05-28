package com.asciipic.search.services;

import com.asciipic.search.dtos.ImagePostDTO;
import org.springframework.util.MultiValueMap;

public interface ComposeImageDTOService {
    ImagePostDTO composeImageDTO(MultiValueMap<String, String> requestParams) throws Exception;
}
