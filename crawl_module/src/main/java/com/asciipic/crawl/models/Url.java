package com.asciipic.crawl.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "urls")
public class Url implements Serializable{
    @Id
    @GeneratedValue(generator = "UrlsSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "UrlsSeq", sequenceName = "URLS_ID_SEQ", allocationSize = 1)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    public Url() {
    }

    public Url(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}