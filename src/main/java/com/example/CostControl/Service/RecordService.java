package com.example.CostControl.Service;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.*;
import com.example.CostControl.Repository.CategoryRepository;
import com.example.CostControl.Repository.RecordRepository;
import com.example.CostControl.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;


    public RecordService(RecordRepository recordRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.recordRepository = recordRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Record getRecordById(Long id) {
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public boolean isRecordExistsById(Long id) {
        return recordRepository.existsById(id);
    }

    public void deleteRecordById(Long id) {
        if (!isRecordExistsById(id)) {
            throw new RecordNotFoundException(id);
        } else {
            recordRepository.deleteById(id);
        }

    }

    public Record addNewRecord(Record record) {
        //try {
            recordRepository.save(record);

        //} catch (Exception e) {
           // throw new IncorrectInputDataException(record.toString());
        //}
        return record;
    }

    public List<Record> getRecordsByCategory(Category category) {
        List<Record> records = recordRepository.findRecordsByCategory(category);
        return records;
    }

    public List<Record> getRecordsByUser(User user) {
        List<Record> records = recordRepository.findRecordsByUser(user);
        return records;
    }

    public List<Record> getRecordsByUserAndCategory(User user, Category category) {
        List<Record> records = recordRepository.findRecordsByUserAndCategory(user, category);
        return records;
    }
}
