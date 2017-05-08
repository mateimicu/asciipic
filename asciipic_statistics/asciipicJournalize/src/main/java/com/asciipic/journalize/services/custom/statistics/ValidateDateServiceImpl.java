package com.asciipic.journalize.services.custom.statistics;

import com.asciipic.journalize.model.InformationJSON;
import sun.net.util.IPAddressUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidateDateServiceImpl {
    public String validate(InformationJSON informationJSON) {
        //ip address
        if (!informationJSON.getIp().equals("")) {
            if (!IPAddressUtil.isIPv4LiteralAddress(informationJSON.getIp()) && !IPAddressUtil.isIPv6LiteralAddress(informationJSON.getIp())) {
                return "Not a valid IP adress!";
            }
        }

        //tag
        if (!informationJSON.getTag().equals("")) {
            if (!informationJSON.getTag().matches("[A-Za-z0-9]+")) {
                return "You have inserted a wrong tag. Please try again!";
            }
        }

        //postDate
        if (!informationJSON.getPostDate().equals("")) {
            try {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                df.setLenient(false);
                df.parse(informationJSON.getPostDate());
            } catch (ParseException e) {
                System.out.println("Something is wrong!");
            }
        }

        //width
        if (!informationJSON.getPictureSizeWidth().equals("")) {
            if (!informationJSON.getPictureSizeWidth().matches("[0-9]+")) {
                return "You have inserted a wrong width. Please try again!";
            }
        }

        //height
        if (!informationJSON.getPictureSizeHeight().equals("")) {
            if (!informationJSON.getPictureSizeHeight().matches("[0-9]+")) {
                return "You have inserted a wrong height. Please try again!";
            }
        }

        //time
        if (!informationJSON.getTime().equals("")) {
            if (Integer.getInteger(informationJSON.getTime()) < 0) {
                return "You have inserted a negative time. Please try again!";
            }
        }

        return null;
    }
}
