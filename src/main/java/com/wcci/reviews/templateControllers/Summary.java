package com.wcci.reviews.templateControllers;

import com.wcci.reviews.respositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Summary {
    final private CategoryRepository categoryRepository;

    public Summary(final @Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/summary.html")
    public String displaySummary(final Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "summary";
    }
}
