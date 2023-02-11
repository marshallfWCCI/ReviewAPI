package com.wcci.reviews.entities;

import java.time.LocalDateTime;

public class HashTag {
    final private String name;
    final private LocalDateTime creationTime;

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
