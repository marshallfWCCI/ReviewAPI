package com.wcci.reviews.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Review {

    // This is a number which starts at 1 for the first review, and then the database
    // increases for every following review
    @Id @GeneratedValue()
    private long id;


    private String title;
    private String author;

    @ManyToOne()
    private Category category;

    @Lob
    private String text;

    @ManyToMany()
    @JoinTable()
    private Collection<HashTag> tags = new HashSet<>(); // Set doesn't care about order

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public void removeTag(final HashTag tag) {
        tags.remove(tag);
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
