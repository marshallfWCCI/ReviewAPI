package com.wcci.reviews.restControllers;

import com.wcci.reviews.entities.HashTag;
import com.wcci.reviews.entities.Review;
import com.wcci.reviews.respositories.HashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TagController {
    final HashTagRepository hashTagRepository;

    public TagController(final @Autowired HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    @GetMapping("/tags")
    public Iterable<HashTag> getHashTags() {
        return hashTagRepository.findAll();
    }

    @GetMapping("/tags/{tag_id}")
    public Iterable<Review> getReviewsForTag(@PathVariable final String tag_id) {
        return hashTagRepository.findById(tag_id)
                .map((tag) -> tag.getReviews())
                .orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find tag " + tag_id);
                });
    }
}
