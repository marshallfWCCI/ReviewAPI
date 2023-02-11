package com.wcci.reviews.entities;

public class Category {
    final private String categoryName;
    final private String categoryDescription;

    public Category(final String categoryName, final String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public String getName() {
        return categoryName;
    }

    public String getDescription() {
        return categoryDescription;
    }
}
