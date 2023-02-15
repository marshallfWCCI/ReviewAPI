package com.wcci.reviews.restControllers;

import com.wcci.reviews.entities.Category;
import com.wcci.reviews.entities.Review;
import com.wcci.reviews.respositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

// Hey, I'm defining a REST api here
@RestController
public class CategoryController {
    // A repository is where you store things
    final private CategoryRepository categoryRepository;

    public CategoryController(final @Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Hey, if somebody wants a list of categories, do a GET to /categories.
    @GetMapping("/categories")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public Category postCategory(final @RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // The path below has a *fixed* part "/categories/" and a *variable* part "{category_id}".
    // The variable part can become a @PathVariable.
    @GetMapping("/categories/{category_id}")
    public Collection<Review> getCategory(@PathVariable final String category_id) {
        return categoryRepository.findById(category_id)
                .map((category) -> category.getReviews())
                .orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find category " + category_id);
                });
    }

    @DeleteMapping("/categories/{category_id}")
    public void deleteCategory(@PathVariable final String category_id) {
        categoryRepository.findById(category_id)
                .ifPresentOrElse(
                        (category) -> categoryRepository.delete(category),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot delete nonexistent category " + category_id);
                        });
    }
}
