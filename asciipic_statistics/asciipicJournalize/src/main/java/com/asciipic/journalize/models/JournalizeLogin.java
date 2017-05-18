package com.asciipic.journalize.models;

import com.asciipic.journalize.models.Journalize;
import com.asciipic.journalize.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "j_login")
public class JournalizeLogin {
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

    @NotNull
    @Column(name = "user_agent", length = 32)
    private String userAgent;

    public JournalizeLogin(Journalize journalize) {
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
