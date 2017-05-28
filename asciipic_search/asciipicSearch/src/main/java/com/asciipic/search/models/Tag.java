package com.asciipic.search.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {
    @Id
    @GeneratedValue(generator = "TagsSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "TagsSeq", sequenceName = "TAGS_ID_SEQ", allocationSize = 1)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    public Tag() {
    }

    public Tag(String name) {
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