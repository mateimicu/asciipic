package com.asciipic.journalize.controller;

import com.asciipic.journalize.models.*;
import com.asciipic.journalize.services.all.statistics.*;
import com.asciipic.journalize.services.custom.statistics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/statistics")
public class Controller {

    private ComposeJsonImpl composeJson = new ComposeJsonImpl();
    private InterrogationService interrogationService = new InterrogationServiceImpl();
    private DrawServiceImp drawService = new DrawServiceImp();
    private ValidateDateServiceImpl validateDateService = new ValidateDateServiceImpl();

    @Autowired
    private JournalizeService journalizeService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private LogoutService logoutService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private FilterService filterService;


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

        //to do:
        //trimitere informat ciudat a pozelor (inca nu stiu daca trnsformate in ascii sau nu)
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<List<JournalizeLogin>> getAllLogin() {

        List<JournalizeLogin> journalizeLogins = loginService.getAll();
        if (journalizeLogins.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<JournalizeLogin>>(journalizeLogins, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<List<JournalizeLogout>> getAllLogout() {

        List<JournalizeLogout> journalizeLogouts = logoutService.getAll();
        if (journalizeLogouts.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<JournalizeLogout>>(journalizeLogouts, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity<List<JournalizeRegister>> getAllRegister() {
        List<JournalizeRegister> journalizeRegisters = registerService.getAll();
        if (journalizeRegisters.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<JournalizeRegister>>(journalizeRegisters, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/crawl", method = RequestMethod.GET)
    public ResponseEntity<List<JournalizeCrawl>> getAllCrawl() {

        List<JournalizeCrawl> journalizeCrawls = crawlService.getAll();
        if(journalizeCrawls.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<JournalizeCrawl>>(journalizeCrawls, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/journalize", method = RequestMethod.GET)
    public ResponseEntity<List<Journalize>> getAllJournalize() {
        List<Journalize> journalizes = journalizeService.getAll();
        if(journalizes.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Journalize>>(journalizes, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<JournalizeSearch>> getAllSearch() {
        List<JournalizeSearch> journalizeSearches = searchService.getAll();
        if(journalizeSearches.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<JournalizeSearch>>(journalizeSearches, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ResponseEntity<List<JournalizeFilter>> getAllFilters() {

        List<JournalizeFilter> journalizeFilters = filterService.getAll();
        if(journalizeFilters.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<JournalizeFilter>>(journalizeFilters, HttpStatus.CREATED);
    }

}
