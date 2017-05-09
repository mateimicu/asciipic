package com.asciipic.journalize.model;

import javax.persistence.*;
import java.io.Serializable;


public class InformationJSON implements Serializable {
    private String mainCommand;
    private String ip;
    private String tag;
    private String postDate;
    private String pictureSizeHeight;
    private String pictureSizeWidth;
    private String userAgent;
    private String filtersType;
    private String causes;
    private String time;
    private String typeOfTime;

    public String getMainCommand() {
        return mainCommand;
    }

    public String getIp() {
        return ip;
    }

    public String getTag() {
        return tag;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPictureSizeHeight() {
        return pictureSizeHeight;
    }

    public String getPictureSizeWidth() {
        return pictureSizeWidth;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getFiltersType() {
        return filtersType;
    }

    public String getCauses() {
        return causes;
    }

    public String getTime() {
        return time;
    }

    public String getTypeOfTime() {
        return typeOfTime;
    }

    public void setMainCommand(String mainCommand) {
        this.mainCommand = mainCommand;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public void setPictureSizeHeight(String pictureSizeHeight) {
        this.pictureSizeHeight = pictureSizeHeight;
    }

    public void setPictureSizeWidth(String pictureSizeWidth) {
        this.pictureSizeWidth = pictureSizeWidth;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setFiltersType(String filtersType) {
        this.filtersType = filtersType;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTypeOfTime(String typeOfTime) {
        this.typeOfTime = typeOfTime;
    }

    @Override
    public String toString() {
        return String.format("InformationJSON\n{\nmainCommand='%s', \nip='%s', \ntag='%s', \npostDate='%s', \npictureSizeHeight='%s', \npictureSizeWidth='%s', \nuserAgent='%s', \nfiltersType='%s', \ncauses='%s', \ntime='%s', \ntypeOfTime='%s'\n}", mainCommand, ip, tag, postDate, pictureSizeHeight, pictureSizeWidth, userAgent, filtersType, causes, time, typeOfTime);
    }
}
