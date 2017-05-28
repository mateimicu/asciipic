package com.asciipic.journalize.dtos;

import java.util.Date;

public class JournalizeFilterDTO {
    private String actionType;
    private Date actionDate;
    private UserDTO userDetails;
    private Date imagePostDate;
    private long height;
    private long width;
    //maybe more details
    private String typeFilter;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public UserDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDTO userDetails) {
        this.userDetails = userDetails;
    }

    public Date getImagePostDate() {
        return imagePostDate;
    }

    public void setImagePostDate(Date imagePostDate) {
        this.imagePostDate = imagePostDate;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }
}
