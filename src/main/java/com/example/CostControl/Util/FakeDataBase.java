package com.example.CostControl.Util;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDataBase {
    private List<User> users = new ArrayList<>();

    private List<Record> records = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();


    public List<User> getUsers() {
        return users;
    }

    public List<Record> getRecords() {
        return records;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
