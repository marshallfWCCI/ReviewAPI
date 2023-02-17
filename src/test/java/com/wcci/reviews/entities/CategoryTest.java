package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {


    // Category is a boring class. All it does is remember two strings, so that's all there is to test.
    // The first line of every test should construct an instance of the class being tested.
    // The last line of every test should verify something about that object
    // In between you can modify the object.
    @Test
    public void test() {
        final Category category = new Category("Romances", "Happily-ever-after, or your money back!");
        assertEquals("Romances", category.getName());
        assertEquals("Happily-ever-after, or your money back!", category.getDescription());
    }

}