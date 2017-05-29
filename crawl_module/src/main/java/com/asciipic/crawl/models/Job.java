package com.asciipic.crawl.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexandru on 5/14/2017.
 */
@Entity
@Table(name = "jobs")
public class Job implements Serializable{
    @Id
    @GeneratedValue(generator = "JobsSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "JobsSeq", sequenceName = "JOBS_ID_SEQ", allocationSize = 1)
    private long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date postDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_date")
    private Date finishDate;

    public Job() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

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
