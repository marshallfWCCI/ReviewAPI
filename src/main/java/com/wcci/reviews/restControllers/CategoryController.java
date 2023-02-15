package com.wcci.reviews.restControllers;

import com.wcci.reviews.entities.Category;
import com.wcci.reviews.entities.Review;
import com.wcci.reviews.respositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class CategoryController {
    final CategoryRepository categoryRepository;

    public CategoryController(final @Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category postCategory(final @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/categories/{category_id}")
    public Collection<Review> getCategory(@PathVariable final String category_id) {
        return categoryRepository.findById(category_id)
                .map((category) -> category.getReviews())
                .orElseGet(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Cannot find category " + category_id);
                });
    }

    @DeleteMapping("/categories/{category_id}")
    public void deleteCategory(@PathVariable final String category_id) {
        categoryRepository.findById(category_id).ifPresent((category) -> categoryRepository.delete(category));
    }
}
