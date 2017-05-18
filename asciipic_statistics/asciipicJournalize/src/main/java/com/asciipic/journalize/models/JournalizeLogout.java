package com.asciipic.journalize.models;

import com.asciipic.journalize.models.Journalize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "j_logout")
public class JournalizeLogout {
    @Id
    @Column(name="journalize_id")
    private long id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journalize_id", referencedColumnName = "id")
    private Journalize journalize;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "cause", length = 32)
    private String cause;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JournalizeLogout(Journalize journalize) {
        this.journalize = journalize;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
