package com.example.CostControl.Controller;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public Category getCategoryById(@RequestParam("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/category")
    public void deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PostMapping("/category")
    public Category addNewCategory(@RequestParam("categoryName") String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryService.saveNewCategory(category);
        return category;
    }


}
