package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTagTest {
    @Test
    public void test() {
        final HashTag hashTag = new HashTag("Recommended");
        assertEquals("Recommended", hashTag.getName());
    }

}