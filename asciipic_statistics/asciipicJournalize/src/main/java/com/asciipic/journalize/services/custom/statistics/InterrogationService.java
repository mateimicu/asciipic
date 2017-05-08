package com.asciipic.journalize.services.custom.statistics;

import com.asciipic.journalize.model.InformationJSON;

public interface InterrogationService {
    int getTotalNumberOfRecordingsFromCustomTable(String table);

    int getTheNumberOfRecordingsWithPropertiesFromCustomTable(InformationJSON table);
}
