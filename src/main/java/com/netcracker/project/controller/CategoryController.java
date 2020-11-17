package com.netcracker.project.controller;

import com.netcracker.project.entity.Category;
import com.netcracker.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for category
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    /**
     * Category repository
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructor
     */
    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Gets categories
     * @return all categories
     */
    @GetMapping
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
