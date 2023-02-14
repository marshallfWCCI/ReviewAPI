package com.wcci.reviews.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(indexes = @Index(columnList = "category_name"))
public class Review {
    @Id @GeneratedValue()
    private long id;

    private String category_name;

    private String title;
    private String author;

    @Lob
    private String text;

    @ManyToMany()
    @JoinTable()
    private Collection<HashTag> tags = new HashSet<>();

    public void removeTag(final HashTag tag) {
        tags.remove(tag);
    }

    // Required by JPA
    protected Review() {
    }

    public void setCategoryName(final String category_name) {
        this.category_name = category_name;
    }

    public Review(final String category_name, final String title, final String author, final String text) {
        this.category_name = category_name;
        this.title = title;
        this.author = author;
        this.text = text;
    }

    public String getCategoryName() {
        return category_name;
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
