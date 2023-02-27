package com.wcci.reviews.templateControllers;

import com.wcci.reviews.respositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// What is the *job* of this class?
// It's basically:
//   * To know which template should be used for which endpoint.
//   * How to populate the model with the information that template needs
@Controller
public class Summary {
    final private CategoryRepository categoryRepository;

    public Summary(final @Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Yes, a better endpoint would called /categorySummary.html, but somebody already told the public that the data
    // was available at /summary.html so we can't stop supporting it now.
    @GetMapping(path = {"/summary.html", "/categorySummary.html"})
    public String displaySummary(final Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorySummary";
    }
}
