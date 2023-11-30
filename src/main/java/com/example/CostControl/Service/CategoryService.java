package com.example.CostControl.Service;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Exception.CategoryNotFoundException;
import com.example.CostControl.Exception.IncorrectInputDataException;
import com.example.CostControl.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final RecordService recordService;

    public CategoryService(CategoryRepository categoryRepository, RecordService recordService/*, CategoryService categoryService*/) {
        this.categoryRepository = categoryRepository;
        this.recordService = recordService;
    }




    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void deleteCategoryById(Long id) {
        if (!isCategoryExistsById(id)) {
            throw new CategoryNotFoundException(id);
        } else {
            List<Record> records = recordService.getRecordsByCategory(getCategoryById(id));
            for (Record record:records) {
                record.setCategory(null);
            }
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
