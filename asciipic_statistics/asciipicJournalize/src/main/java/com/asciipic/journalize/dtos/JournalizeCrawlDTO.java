package com.asciipic.journalize.dtos;

import java.util.Date;

public class JournalizeCrawlDTO {
    private String actionType;
    private Date actionDate;
    private UserDTO userDetails;
    private JobDTO jobDetails;

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

    public JobDTO getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(JobDTO jobDetails) {
        this.jobDetails = jobDetails;
    }
}
