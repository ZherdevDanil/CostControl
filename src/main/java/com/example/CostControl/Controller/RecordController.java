package com.example.CostControl.Controller;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.CategoryNotFoundException;
import com.example.CostControl.Exception.UserNotFoundException;
import com.example.CostControl.Service.CategoryService;
import com.example.CostControl.Service.RecordService;
import com.example.CostControl.Service.UserService;
import com.example.CostControl.Util.DateTimeFormatter;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class RecordController {
    private final RecordService recordService;
    private final UserService userService;
    private final CategoryService categoryService;

    private final DateTimeFormatter dateTimeFormatter;

    public RecordController(RecordService recordService, UserService userService, CategoryService categoryService, DateTimeFormatter dateTimeFormatter) {
        this.recordService = recordService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @GetMapping("/record/{record_id}")
    public Record getRecordById(@PathVariable("record_id") long id){
        Record record = recordService.getRecordById(id);
        return record;
    }


    @DeleteMapping("/record/{record_id}")
    public void deleteRecordById(@PathVariable("record_id") long id){
        recordService.deleteRecordById(id);
    }
/*
    @PostMapping("/record")
    public Record addNewRecord(@RequestBody Record record){
        User user = userService.getUserById(record.getUserId());
        if (user == null){
            throw new UserNotFoundException(record.getUserId());

        }
        Category category = categoryService.getCategoryById(record.getCategoryId());
        if (category == null){
            throw new CategoryNotFoundException(record.getCategoryId());
        }
        recordService.addNewRecord(record);
        return record;
    }*/

    @PostMapping("/record")
    public Record addNewRecord(@RequestParam Long userId,@RequestParam Long categoryId,@RequestParam String recordCreationDateTime, @RequestParam double expenseAmount) throws ParseException {
        Record newRecord = new Record();
        User user = userService.getUserById(userId);
        if (user == null){
            throw new UserNotFoundException(userId);

        }
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null){
            throw new CategoryNotFoundException(categoryId);
        }
        newRecord.setId(recordService.generateNewUniqueRecordId());
        newRecord.setUserId(userId);
        newRecord.setCategoryId(categoryId);
        newRecord.setRecordCreationDateTime(dateTimeFormatter.formatStringToDate(recordCreationDateTime));
        newRecord.setExpenseAmount(expenseAmount);

        recordService.addNewRecord(newRecord);
        return newRecord;
    }

    @GetMapping("/record")
    public List<Record> getRecordsByUserIdAndCategoryIdOrEither(@RequestParam(required = false) Long userId , @RequestParam(required = false) Long categoryId){
        if (userId == null && categoryId == null){
            throw new IllegalArgumentException("UserId or CategoryId must be provided ");
        }
        if (userId == null){
            return recordService.getRecordsByCategoryId(categoryId);
        } else if (categoryId == null) {
            return recordService.getRecordsByUserId(userId);
        } else {
            return recordService.getRecordsByUserIdAndCategoryId(userId,categoryId);
        }

    }
}
