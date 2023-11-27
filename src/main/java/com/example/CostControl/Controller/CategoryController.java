package com.example.CostControl.Controller;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Service.CategoryService;
import com.example.CostControl.Service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    private final RecordService recordService;

    public CategoryController(CategoryService categoryService, RecordService recordService) {
        this.categoryService = categoryService;
        this.recordService = recordService;
    }

    @GetMapping("/category")
    public List<Category> getListOfCategory(){
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/category")
    public void deleteCategory(@RequestParam("id") long id){
        categoryService.deleteById(id);
    }

    @PostMapping("/category")
    public Category addNewCategory(@RequestParam("categoryName") String categoryName){
        Category category = new Category();
        long newUserId = categoryService.generateNewUniqueCategoryId();
        category.setId(newUserId);
        category.setCategoryName(categoryName);
        categoryService.saveNewCategory(category);
        return category;
    }


}
