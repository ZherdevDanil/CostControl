package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    private final FakeDataBase fakeDataBase;

    public CategoryRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public List<Category> findAll(){
        return fakeDataBase.getCategories();
    }

    public List<Long> getListOfCategoryId(){
        List<Long> list=new ArrayList<>();
        for (Category category:fakeDataBase.getCategories()) {
            list.add(category.getId());
        }
        return list;
    }

    public Category findById(long id){
        Category category1=new Category();
        for (Category category:fakeDataBase.getCategories()) {
            if (category.getId()==id){
                category1=category;
            }
        }
        return category1;
    }

    public void delete(long id){
        fakeDataBase.getCategories().remove(findById(id));
    }

    public void save(Category category){
        fakeDataBase.getCategories().add(category);
    }


}
