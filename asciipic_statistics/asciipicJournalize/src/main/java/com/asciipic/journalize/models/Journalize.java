package com.asciipic.journalize.models;

import jdk.nashorn.internal.scripts.JO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "journalize")
public class Journalize {
    @Id
    @GeneratedValue(generator = "JournalizeSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "JournalizeSeq", sequenceName = "JOURNALIZE_ID_SEQ", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "action", length = 32)
    private String action;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "action_date")
    private Date actionDate;

    public Journalize(){

    }

    public Journalize(String action) {
        this.action = action;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
