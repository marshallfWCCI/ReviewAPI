package com.wcci.reviews.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Review {
    final private Category category;
    final private String title;
    final private String author;
    final private String text;
    final private Set<HashTag> tags = new HashSet<>();

    public Review(final Category category, final String title, final String author, final String text) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void addTag(final HashTag tag) {
        tags.add(tag);
    }

    public Collection<HashTag> getTags() {
        return tags;
    }
}
