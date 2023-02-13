package com.wcci.reviews.restControllers;

import com.wcci.reviews.entities.HashTag;
import com.wcci.reviews.entities.Review;
import com.wcci.reviews.respositories.HashTagRepository;
import com.wcci.reviews.respositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReviewController {
    final ReviewRepository reviewRepository;
    final HashTagRepository tagRepository;

    public ReviewController(
            final @Autowired ReviewRepository reviewRepository,
            final @Autowired HashTagRepository tagRepository) {
        this.reviewRepository = reviewRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/reviews")
    public Iterable<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/reviews/{review_id}")
    public Optional<Review> getReviewByID(final @PathVariable long review_id) {
        return reviewRepository.findById(review_id);
    }

    @GetMapping("/reviews/{review_id}/tags")
    public Optional<Iterable<HashTag>> getTagsForReviewByID(final @PathVariable long review_id) {
        return reviewRepository.findById(review_id)
                .map((review) -> review.getTags());
    }

    @PostMapping("/reviews/{review_id}/tags/{tag_id}")
    public void addTagToReview(
            final @PathVariable long review_id,
            final @PathVariable String tag_id) {
        final HashTag tag = tagRepository
                .findById(tag_id)
                .orElseGet(() -> tagRepository.save(new HashTag(tag_id)));

        final Optional<Review> reviewByID = reviewRepository.findById(review_id);
        reviewByID.map((final Review review) -> {
            review.getTags().add(tag);
            return reviewRepository.save(review);
        });
    }

    @DeleteMapping("/reviews/{review_id}/tags/{tag_id}")
    public void removeTagFromReview(
            final @PathVariable long review_id,
            final @PathVariable String tag_id) throws Exception {
        final Optional<HashTag> tag = tagRepository.findById(tag_id);

        if (tag.isEmpty()) throw new Exception("Unable to find tag: " + tag_id);

        final Optional<Review> reviewByID = reviewRepository.findById(review_id);
        reviewByID.map((final Review review) -> {
            review.getTags().remove(tag);
            return reviewRepository.save(review);
        });
    }

    @PostMapping("/reviews")
    public @ResponseBody Review postReview(final @RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @PutMapping("/reviews/{review_id}")
    public void putReview(@PathVariable final long review_id, final @RequestBody Review review) throws Exception {
        if (review.getId() != review_id)
            throw new Exception("Review body has id " + review.getId() + " but url had id " + review_id);
        reviewRepository.save(review);
    }
}
