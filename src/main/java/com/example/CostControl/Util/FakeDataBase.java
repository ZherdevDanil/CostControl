package com.example.CostControl.Util;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDataBase {

    //private final DateTimeFormatter dateTimeFormatter;
    private final List<User> users = new ArrayList<>();

    private final List<Record> records = new ArrayList<>();

    private final List<Category> categories = new ArrayList<>();
/*
    public FakeDataBase(DateTimeFormatter dateTimeFormatter) throws ParseException {
        this.dateTimeFormatter = dateTimeFormatter;
        users.add(new User(1L, "Jack"));
        users.add(new User(5L, "Billy"));

        categories.add(new Category(2L, "Utilities"));
        categories.add(new Category(4L, "Groceries"));


        records.add(new Record(1L, users.get(0).getId(), categories.get(0).getId(), dateTimeFormatter.formatStringToDate("15.11.2017 14:34"), 333.34));
        records.add(new Record(2L, 1L, 4L, dateTimeFormatter.formatStringToDate("17.09.2018 16:58"), 124.12));
        records.add(new Record(3L, 5L, 2L, dateTimeFormatter.formatStringToDate("11.04.2011 23:12"), 897.78));


    }*/


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
