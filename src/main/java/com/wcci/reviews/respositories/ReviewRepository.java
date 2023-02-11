package com.wcci.reviews.respositories;

import com.wcci.reviews.entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
