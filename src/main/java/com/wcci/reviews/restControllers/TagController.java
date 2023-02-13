package com.wcci.reviews.restControllers;

import com.wcci.reviews.entities.Category;
import com.wcci.reviews.entities.HashTag;
import com.wcci.reviews.entities.Review;
import com.wcci.reviews.respositories.CategoryRepository;
import com.wcci.reviews.respositories.HashTagRepository;
import com.wcci.reviews.respositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Optional<Iterable<Review>> getReviewsForTag(@PathVariable final String tag_id) {
        return hashTagRepository.findById(tag_id).map((tag) -> tag.getReviews());
    }
}
