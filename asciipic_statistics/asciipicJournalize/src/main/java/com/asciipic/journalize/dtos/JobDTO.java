package com.asciipic.journalize.dtos;

import com.asciipic.journalize.connection.DataBaseConnection;

import java.util.Date;

public class JobDTO {
    private Date startDate;
    private Date finishDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
