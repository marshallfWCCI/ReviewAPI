package com.wcci.reviews.restControllers;

import com.wcci.reviews.entities.Category;
import com.wcci.reviews.entities.HashTag;
import com.wcci.reviews.respositories.CategoryRepository;
import com.wcci.reviews.respositories.HashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
