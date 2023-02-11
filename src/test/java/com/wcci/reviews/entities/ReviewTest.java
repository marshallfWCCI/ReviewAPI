package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    @Test
    public void testConstructor() {
        final Category category = new Category("Movie");
        final String title = "Princess Bride";
        final String author = "William Golding";
        final String text = "Entertaining on many levels";
        final Review review = new Review(category, title, author, text);

        assertEquals(category, review.getCategory());
        assertEquals(title, review.getTitle());
        assertEquals(author, review.getAuthor());
        assertEquals(text, review.getText());
    }

    @Test
    public void testTags() {
        final Category category = new Category("Movie");
        final String title = "Princess Bride";
        final String author = "William Golding";
        final String text = "Entertaining on many levels";
        final Review review = new Review(category, title, author, text);

        final HashTag tag1 = new HashTag("For kids");
        final HashTag tag2 = new HashTag("For adults");
        review.addTag(tag1);
        review.addTag(tag2);
        assertTrue(review.getTags().contains(tag1));
        assertTrue(review.getTags().contains(tag2));
        assertEquals(2, review.getTags().size());
    }

}