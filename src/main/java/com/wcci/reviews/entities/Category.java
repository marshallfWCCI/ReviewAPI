package com.wcci.reviews.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

// Hey spring, store me in a database
@Entity()
public class Category {
    // Hey spring, the "name" field is the unique key to the table
    @Id
    private String name;

    // I could have put @Column here, but Spring by default thinks every field should be a column in the database
    private String description;

    // The "mappedBy" really means that the other class is in the driving seat.
    @OneToMany(mappedBy="category")
    @JsonIgnore // This field should *not* be returned in the JSON if someone just wants to return a Category
    private Collection<Review> reviews = new HashSet<>();

    public Category(final String categoryName, final String categoryDescription) {
        this.name = categoryName;
        this.description = categoryDescription;
    }

    // JPA requires a public or protected zero-argument constructor
    protected Category() {
    }

    // Note: there is no "addReview(review){}" here.

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name) && description.equals(category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
