package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HashTagTest {
    @Test
    public void test() {
        final HashTag hashTag = new HashTag("Recommended");
        assertEquals("Recommended", hashTag.getName());
    }

    @Test
    public void testCreation() {
        final LocalDateTime before = LocalDateTime.now();
        final HashTag hashTag = new HashTag("Recommended");
        final LocalDateTime after = LocalDateTime.now();

        assertFalse(before.isAfter(hashTag.getCreationTime()));
        assertFalse(hashTag.getCreationTime().isAfter(after));
    }
}