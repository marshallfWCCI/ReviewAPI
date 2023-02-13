package com.wcci.reviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "hashtags")
public class HashTag {
    @Id
    @Column(name="name")
    private String name;

    @Column(name="creation_time")
    @JsonIgnore
    private LocalDateTime creationTime;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Collection<Review> reviews;

    // Required by JPA
    protected HashTag() {
    }

    public HashTag(final String name) {
        this.name = name;
        this.creationTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return name.equals(hashTag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
