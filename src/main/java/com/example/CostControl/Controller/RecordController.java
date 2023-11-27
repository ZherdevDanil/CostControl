package com.example.CostControl.Controller;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.CategoryNotFoundException;
import com.example.CostControl.Exception.UserNotFoundException;
import com.example.CostControl.Service.CategoryService;
import com.example.CostControl.Service.RecordService;
import com.example.CostControl.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecordController {
    private final RecordService recordService;
    private final UserService userService;
    private final CategoryService categoryService;

    public RecordController(RecordService recordService, UserService userService, CategoryService categoryService) {
        this.recordService = recordService;
        this.userService = userService;
        this.categoryService = categoryService;
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

    @PostMapping("/record")
    public void addNewRecord(@RequestBody Record record){
        User user = userService.getUserById(record.getUserId());
        if (user == null){
            throw new UserNotFoundException(record.getUserId());

        }
        Category category = categoryService.getCategoryById(record.getCategoryId());
        if (category == null){
            throw new CategoryNotFoundException(record.getCategoryId());
        }
        recordService.addNewRecord(record);
    }
}
