package com.wcci.reviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "hashtags")
public class HashTag {
    @Id
    @Column(name="name")
    private String name;

    @Column(name="creation_time")
    @JsonIgnore
    private LocalDateTime creationTime;

    @ManyToMany
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
}
