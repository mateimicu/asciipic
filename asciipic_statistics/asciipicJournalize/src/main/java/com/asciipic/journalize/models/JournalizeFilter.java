package com.asciipic.journalize.models;

import com.asciipic.journalize.models.Journalize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "j_filters")
public class JournalizeFilter {
    @Id
    @Column(name = "journalize_id")
    private long id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journalize_id", referencedColumnName = "id")
    private Journalize journalize;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "image_id")
    private long imageId;

    @NotNull
    @Column(name = "type", length = 32)
    private String type;


    public JournalizeFilter(Journalize journalize) {
        this.journalize = journalize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Journalize getJournalize() {
        return journalize;
    }

    public void setJournalize(Journalize journalize) {
        this.journalize = journalize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
