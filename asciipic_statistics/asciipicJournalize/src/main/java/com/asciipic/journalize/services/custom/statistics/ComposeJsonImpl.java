package com.asciipic.journalize.services.custom.statistics;

import com.asciipic.journalize.model.InformationJSON;

import java.util.Map;

public class ComposeJsonImpl {
    public InformationJSON composeJson(Map<String, String> requestParams) throws Exception {
        InformationJSON informationJSON = new InformationJSON();

        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (!isAValidKey(entry.getKey())) {
                throw new Exception("Option not valid");
            }
        }

        informationJSON.setMainCommand(requestParams.get("mainCommand") == null ? "" : requestParams.get("mainCommand"));
        informationJSON.setIp(requestParams.get("ip") == null ? "" : requestParams.get("ip"));
        informationJSON.setTag(requestParams.get("tag") == null ? "" : requestParams.get("tag"));
        informationJSON.setPostDate(requestParams.get("postDate") == null ? "" : requestParams.get("postDate"));
        informationJSON.setPictureSizeHeight(requestParams.get("pictureSizeHeight") == null ? "" : requestParams.get("pictureSizeHeight"));
        informationJSON.setPictureSizeWidth(requestParams.get("pictureSizeWidth") == null ? "" : requestParams.get("pictureSizeWidth"));
        informationJSON.setUserAgent(requestParams.get("userAgent") == null ? "" : requestParams.get("userAgent"));
        informationJSON.setFiltersType(requestParams.get("filtersType") == null ? "" : requestParams.get("filtersType"));
        informationJSON.setCauses(requestParams.get("causes") == null ? "" : requestParams.get("causes"));
        informationJSON.setTime(requestParams.get("time") == null ? "" : requestParams.get("time"));
        informationJSON.setTypeOfTime(requestParams.get("typeOfTime") == null ? "" : requestParams.get("typeOfTime"));

        return informationJSON;
    }

    private boolean isAValidKey(String key) {
        if (key.equals("mainCommand") || key.equals("ip") || key.equals("tag") || key.equals("postDate") ||
                key.equals("pictureSizeHeight") || key.equals("pictureSizeWidth") || key.equals("userAgent")
                || key.equals("filtersType") || key.equals("causes") || key.equals("time") || key.equals("typeOfTime")) {
            return true;
        }
        return false;
    }
}
