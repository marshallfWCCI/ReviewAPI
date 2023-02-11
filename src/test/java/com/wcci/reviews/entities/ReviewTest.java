package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
    @Test
    public void test() {
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

}