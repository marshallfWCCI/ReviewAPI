package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    public void test() {
        final Category category = new Category("Romances");
        assertEquals("Romances", category.getName());

    }

}