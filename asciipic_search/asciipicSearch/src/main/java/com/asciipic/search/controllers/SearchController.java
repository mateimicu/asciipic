package com.asciipic.search.controllers;

import com.asciipic.search.dtos.ResponseDTO;
import com.asciipic.search.repositories.ImageRepository;
import com.asciipic.search.repositories.TagRepository;
import com.asciipic.search.services.BuildResponseService;
import com.asciipic.search.services.ComposeImageDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/searches")
public class SearchController {

    @Autowired
    BuildResponseService buildResponseService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> getSearches(@RequestParam MultiValueMap<String, String> requestParams) throws ParseException {
        try {
            List<String> urls = buildResponseService.getResponse(requestParams);
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, "Succes!", urls), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
