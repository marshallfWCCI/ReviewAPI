package com.wcci.reviews.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Review {
    @Id @GeneratedValue()
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "category_name")
    private Category category;

    private String title;
    private String author;
    private String text;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "review_tag",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")
    )
    private Set<HashTag> tags = new HashSet<>();

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

    public Set<HashTag> getTags() {
        return tags;
    }

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}
