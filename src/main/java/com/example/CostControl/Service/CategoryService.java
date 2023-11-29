package com.example.CostControl.Service;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Exception.CategoryNotFoundException;
import com.example.CostControl.Exception.IncorrectInputDataException;
import com.example.CostControl.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void deleteCategoryById(Long id) {
        if (!isCategoryExistsById(id)) {
            throw new CategoryNotFoundException(id);
        } else {
            categoryRepository.deleteById(id);
        }
    }

    public void saveNewCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            throw new IncorrectInputDataException(category.toString());
        }
    }

    public boolean isCategoryExistsById(Long id) {
        return categoryRepository.existsById(id);
    }

}
