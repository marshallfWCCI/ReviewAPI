package com.wcci.reviews.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
public class Category {
    @Id
    private String categoryName;
    private String categoryDescription;

    public Category(final String categoryName, final String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    // JPA requires a public or protected zero-argument constructor
    protected Category() {
    }

    public String getName() {
        return categoryName;
    }

    public String getDescription() {
        return categoryDescription;
    }
}
