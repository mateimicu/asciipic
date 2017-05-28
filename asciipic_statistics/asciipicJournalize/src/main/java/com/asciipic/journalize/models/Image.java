package com.asciipic.journalize.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "crawl_date")
    private Date crawlDate;

    @NotNull
    @Column(name = "url", length = 2048)
    String url;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_date")
    private Date postDate;

    @NotNull
    @Column(name = "height")
    private long height;

    @NotNull
    @Column(name = "width")
    private long width;

    @Column(name = "is_saved")
    private boolean isSaved;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "image", cascade = CascadeType.ALL)
    private List<Tag> tags;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "image", cascade = CascadeType.ALL)
    private SavedImage image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCrawlDate() {
        return crawlDate;
    }

    public void setCrawlDate(Date crawlDate) {
        this.crawlDate = crawlDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public SavedImage getImage() {
        return image;
    }

    public void setImage(SavedImage image) {
        this.image = image;
    }
}
