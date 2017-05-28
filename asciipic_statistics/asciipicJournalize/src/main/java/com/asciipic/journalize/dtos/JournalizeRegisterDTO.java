package com.asciipic.journalize.dtos;

import com.asciipic.journalize.repositories.JournalizeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class JournalizeRegisterDTO {

    private String actionType;
    private Date actionDate;
    private UserDTO userDetails;
    private String ip;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
