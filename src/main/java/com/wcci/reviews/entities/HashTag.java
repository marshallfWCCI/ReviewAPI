package com.wcci.reviews.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class HashTag {
    @Id
    private String name;
    private LocalDateTime creationTime;

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
