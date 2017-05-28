package com.asciipic.journalize.models;

import com.asciipic.journalize.models.Journalize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "j_register")
public class JournalizeRegister {
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
    @Column(name = "ip", length = 32)
    private String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JournalizeRegister(Journalize journalize) {
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
