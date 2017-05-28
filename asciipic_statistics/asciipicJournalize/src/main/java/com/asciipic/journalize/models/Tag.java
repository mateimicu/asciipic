package com.asciipic.journalize.models;

import com.asciipic.journalize.models.Image;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tegs")
public class Tag implements Serializable {
    @EmbeddedId
    private TagId id;

    @MapsId("imageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false, unique = true)
    private Image image;

    public TagId getId() {
        return id;
    }

    public void setId(TagId id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

@Embeddable
class TagId implements Serializable {
    @Column(name = "image_id")
    long imageId;
    @Column(name = "tag_name")
    String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagId tagId = (TagId) o;

        if (imageId != tagId.imageId) return false;
        return tagName.equals(tagId.tagName);
    }

    @Override
    public int hashCode() {
        int result = (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + tagName.hashCode();
        return result;
    }
}
