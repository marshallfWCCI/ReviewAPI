package com.wcci.reviews.entities;

public class Category {
    final private String categoryName;

    public String getName() {
        return categoryName;
    }

    public Category(final String categoryName) {
        this.categoryName = categoryName;
    }
}
