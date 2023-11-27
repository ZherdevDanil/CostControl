package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private final FakeDataBase fakeDataBase;

    public CategoryRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public List<Category> findAll() {
        return fakeDataBase.getCategories();
    }

    public List<Long> getListOfCategoryId() {
        List<Long> list = new ArrayList<>();
        for (Category category : fakeDataBase.getCategories()) {
            list.add(category.getId());
        }
        return list;
    }

    public Optional<Category> findById(long id) {
        return fakeDataBase.getCategories().stream().filter(category -> category.getId() == id).findFirst();
    }

    public void delete(long id) {
        findById(id).ifPresent(fakeDataBase.getCategories()::remove);
    }

    public void save(Category category) {
        fakeDataBase.getCategories().add(category);
    }


}
