package com.wcci.reviews.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity()
public class Category {
    @Id
    private String name;
    private String description;

    @OneToMany()
    private Collection<Review> reviews;

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

}
