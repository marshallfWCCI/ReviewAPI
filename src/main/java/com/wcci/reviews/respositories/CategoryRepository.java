package com.wcci.reviews.respositories;

import com.wcci.reviews.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
