package com.wcci.reviews.respositories;

import com.wcci.reviews.entities.Category;
import org.springframework.data.repository.CrudRepository;

// CRUD
// Create
// Read
// Update
// Delete

// Spring, behind-the-scenes, creates an implementation class which satisifies this interface.
// This saves you *dozens* of hours.
// CrudRepository needs to know:
// * The type of the data
// * The type of the index.
public interface CategoryRepository extends CrudRepository<Category, String> {
}
