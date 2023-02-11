package com.wcci.reviews.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Review {
    @Id @GeneratedValue()
    long id;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;

    private String title;
    private String author;
    private String text;

    @ManyToMany(mappedBy = "reviews")
    private Collection<HashTag> tags = new HashSet<>();

    // Required by JPA
    protected Review() {
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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
