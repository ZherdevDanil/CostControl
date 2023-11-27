package com.example.CostControl.Util;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDataBase {
    private List<User> users = new ArrayList<>();

    private List<Record> records = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();

    public FakeDataBase() throws ParseException {
        users.add(new User(1L,"Jack"));
        users.add(new User(5L,"Billy"));

        categories.add(new Category(2L,"Utilities"));
        categories.add(new Category(4L,"Groceries"));


        records.add(new Record(1L,users.get(0).getId(),categories.get(0).getId(),new SimpleDateFormat("dd.MM.yyyy HH:mm").parse("24.11.2023 12:35"),374.34));

    }


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
