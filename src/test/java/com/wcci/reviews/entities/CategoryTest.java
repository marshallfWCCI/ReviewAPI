package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    public void test() {
        final Category category = new Category("Romances", "Happily-ever-after, or your money back!");
        assertEquals("Romances", category.getName());
        assertEquals("Happily-ever-after, or your money back!", category.getDescription());
    }

}