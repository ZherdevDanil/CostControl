package com.example.CostControl.Controller;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Service.CategoryService;
import com.example.CostControl.Service.RecordService;
import com.example.CostControl.Service.UserService;
import com.example.CostControl.Util.DateTimeFormatter;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@Validated
public class RecordController {
    private final RecordService recordService;
    private final UserService userService;
    private final CategoryService categoryService;

    private final DateTimeFormatter dateTimeFormatter;

    public RecordController(RecordService recordService,
                            UserService userService,
                            CategoryService categoryService,
                            DateTimeFormatter dateTimeFormatter) {
        this.recordService = recordService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @GetMapping("/record/{record_id}")
    public Record getRecordById(@PathVariable("record_id") Long id) {
        Record record = recordService.getRecordById(id);
        return record;
    }


    @DeleteMapping("/record/{record_id}")
    public void deleteRecordById(@PathVariable("record_id") Long id) {
        recordService.deleteRecordById(id);
    }

    @PostMapping("/record")
    public Record addNewRecord(@RequestParam Long userId,
                               @RequestParam Long categoryId,
                               @NotNull(message = "recordCreatianDateTime can't be Null") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") @RequestParam String recordCreationDateTime,
                               @RequestParam Double expenseAmount) throws ParseException {

        Record newRecord = new Record();
        User user = userService.getUserById(userId);
        Category category = categoryService.getCategoryById(categoryId);

        newRecord.setUser(user);
        newRecord.setCategory(category);
        newRecord.setRecordCreationDateTime(dateTimeFormatter.formatStringToDate(recordCreationDateTime));
        newRecord.setExpenseAmount(expenseAmount);

        user.getAccount().setMoneyAmount(user.getAccount().getMoneyAmount() - expenseAmount);

        userService.updateUser(user);
        recordService.addNewRecord(newRecord);


        return newRecord;
    }

    @GetMapping("/record")
    public List<Record> getRecordsByUserIdAndCategoryIdOrEither(@RequestParam(required = false) Long userId,
                                                                @RequestParam(required = false) Long categoryId) {

        if (userId == null && categoryId == null) {
            throw new IllegalArgumentException("UserId or CategoryId must be provided ");
        }
        if (userId == null) {
            return recordService.getRecordsByCategory(categoryService.getCategoryById(categoryId));
        } else if (categoryId == null) {
            return recordService.getRecordsByUser(userService.getUserById(userId));
        } else {
            return recordService.getRecordsByUserAndCategory(userService.getUserById(userId), categoryService.getCategoryById(categoryId));
        }

    }
}
