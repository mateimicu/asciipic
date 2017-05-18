package com.asciipic.journalize.services.custom.statistics;

import com.asciipic.journalize.models.InformationJSON;

public interface InterrogationService {
    int getTotalNumberOfRecordingsFromCustomTable(String table);

    int getTheNumberOfRecordingsWithPropertiesFromCustomTable(InformationJSON table);
}
