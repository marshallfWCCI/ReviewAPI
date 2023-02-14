package com.wcci.reviews.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Review {
    @Id @GeneratedValue()
    private long id;

    @ManyToOne()
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Category category;

    private String title;
    private String author;

    @Lob
    private String text;

    @ManyToMany()
    @JoinTable()
    private Collection<HashTag> tags = new HashSet<>();

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void removeTag(final HashTag tag) {
        tags.remove(tag);
    }

    // Required by JPA
    protected Review() {
    }

    public Review(final Category category, final String title, final String author, final String text) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.text = text;
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

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}
