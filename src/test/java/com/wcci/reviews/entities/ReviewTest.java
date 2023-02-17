package com.wcci.reviews.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


// When some other programmer comes you and asks, "so how do I know what I can do with this Review class of yours"
// "do you have any examples? What are standard ways to use it and what sample values?"
// You can just say -- look the unit tests and don't bother me.

// Suppose they come back to you and say "your class is broken...it doesn't do what it should".
// Instead of saying back to them, "can you take some screenshots and write a paragraph as to what's wrong".
// You can just say "write a unit test that fails but you think it should pass".
// And then you have three choices:
// 1. Ah, I see what you really to try to do, and you're wrong...please read my docs.
// 2. Ah, I see what you really to try to do, and oops my docs deed to be clarified.
// 3. Ooops...let me fix that for you and send you the fix for review.
// No matter what, the new example stays in the test class forever
// (though in the first two cases, there's a comment describing why the actual is what it is and the expected is fixed)

class ReviewTest {
    @Test
    public void testConstructor() {
        // Construct an object to be tested
        final Category category = new Category("Movie", "Moving Images with Sound");
        final String title = "Princess Bride";
        final String author = "William Golding";
        final String text = "Entertaining on many levels";
        final Review review = new Review(category, title, author, text);

        // Maybe do something to it

        // Test stuff about it.
        assertEquals(category.getName(), review.getCategory().getName());
        assertEquals(title, review.getTitle());
        assertEquals(author, review.getAuthor());
        assertEquals(text, review.getText());
    }

    @Test
    public void testTags() {
        final Category category = new Category("Movie", "Moving Images with Sound");
        final String title = "Princess Bride";
        final String author = "William Golding";
        final String text = "Entertaining on many levels";
        final Review review = new Review(category, title, author, text);

        // Now I'm doing stuff to the object
        final HashTag tag1 = new HashTag("For kids");
        final HashTag tag2 = new HashTag("For adults");
        review.addTag(tag1);
        review.addTag(tag2);

        // getTags() contains at least tag1
        assertTrue(review.getTags().contains(tag1));
        // getTags() contains at least tag2
        assertTrue(review.getTags().contains(tag2));
        // getTags() doesn't return anything else
        assertEquals(2, review.getTags().size());
    }

    @Test
    public void removeTags() {
        final Category category = new Category("Movie", "Moving Images with Sound");
        final String title = "Princess Bride";
        final String author = "William Golding";
        final String text = "Entertaining on many levels";
        final Review review = new Review(category, title, author, text);

        // Now I'm doing stuff to the object
        // This should be comprehsible by a non-programmer
        final HashTag tag1 = new HashTag("For kids");
        final HashTag tag2 = new HashTag("For adults");
        review.addTag(tag1);
        review.addTag(tag2);
        review.removeTag(tag1);

        // Now I'm doing stuff to the object
        // This should be comprehsible by a non-programmer

        // getTags() no longer contains tag1
        assertFalse(review.getTags().contains(tag1));
        // getTags() contains at least tag2
        assertTrue(review.getTags().contains(tag2));
        // I'm expecting that there are exactly 1 tag left in the list of tags.
        assertEquals(1, review.getTags().size());

        // You can basically double-check yourself with anything.
        assertEquals(13, 1+3*4);
    }

    @Test
    public void doubleTags() {
        final Category category = new Category("Movie", "Moving Images with Sound");
        final String title = "Princess Bride";
        final String author = "William Golding";
        final String text = "Entertaining on many levels";
        final Review review = new Review(category, title, author, text);

        final HashTag tag1 = new HashTag("Favorite");
        final HashTag tag2 = new HashTag("Favorite");
        // The default implementation of Object.equals() considers tag1 and tag2 different
        // but HashTag.equals() can override .equals() to have a saner criteria

        // If two different users simultaneously add the same tag, the review should only have it once.
        review.addTag(tag1);
        review.addTag(tag2);
        assertEquals(1, review.getTags().size());
    }

}