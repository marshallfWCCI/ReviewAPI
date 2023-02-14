package com.wcci.reviews.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity()
public class Category {
    @Id
    private String name;

    private String description;

    @OneToMany()
    private Collection<Review> reviews = new HashSet<>();

    public Category(final String categoryName, final String categoryDescription) {
        this.name = categoryName;
        this.description = categoryDescription;
    }

    // JPA requires a public or protected zero-argument constructor
    protected Category() {
    }

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
