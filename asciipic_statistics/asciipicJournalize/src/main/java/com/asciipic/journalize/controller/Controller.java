package com.asciipic.journalize.controller;

import com.asciipic.journalize.model.InformationJSON;
import com.asciipic.journalize.services.custom.statistics.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/v1/statistics")
public class Controller {

    private ComposeJsonImpl composeJson = new ComposeJsonImpl();
    private InterrogationService interrogationService = new InterrogationServiceImpl();
    private DrawServiceImp drawService = new DrawServiceImp();
    private ValidateDateServiceImpl validateDateService = new ValidateDateServiceImpl();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> addStudent(@RequestParam Map<String, String> requestParams, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Type", "application/text");

        composeJson = new ComposeJsonImpl();
        try {
            InformationJSON informationJSON = composeJson.composeJson(requestParams);
            if (validateDateService.validate(informationJSON) == null) {
                int totalNumberOfRecords = interrogationService.getTotalNumberOfRecordingsFromCustomTable(informationJSON.getMainCommand());
                if (totalNumberOfRecords == 0) {
                    return new ResponseEntity<String>("No data into in the table!", HttpStatus.BAD_REQUEST);
                }
                int recordsWithProperties = interrogationService.getTheNumberOfRecordingsWithPropertiesFromCustomTable(informationJSON);

                return new ResponseEntity<String>(drawService.draw(totalNumberOfRecords, recordsWithProperties), HttpStatus.OK);
            }

            return new ResponseEntity<String>(validateDateService.validate(informationJSON), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
