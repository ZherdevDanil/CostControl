package com.example.CostControl.Service;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Exception.CategoryNotFoundException;
import com.example.CostControl.Repository.CategoryRepository;
import com.example.CostControl.Repository.UserRepository;
import com.example.CostControl.Util.GenerateRandomValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final GenerateRandomValue generateRandomValue;

    public CategoryService(CategoryRepository categoryRepository,GenerateRandomValue generateRandomValue) {
        this.categoryRepository = categoryRepository;
        this.generateRandomValue=generateRandomValue;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public long generateNewUniqueCategoryId(){
        return generateRandomValue.generateRandomUniqueNumber(categoryRepository.getListOfCategoryId());
    }

    public Category getCategoryById(long id){
        return categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));
    }

    public void deleteById(long id){
        categoryRepository.delete(id);
    }

    public void saveNewCategory(Category category){
        categoryRepository.save(category);
    }

}
