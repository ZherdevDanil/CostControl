package com.example.CostControl.Service;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.*;
import com.example.CostControl.Repository.CategoryRepository;
import com.example.CostControl.Repository.RecordRepository;
import com.example.CostControl.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    @Autowired
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
        try {
            recordRepository.save(record);

        } catch (Exception e) {
            throw new IncorrectInputDataException(record.toString());
        }
        return record;
    }

    public List<Record> getRecordsByCategory(Category category) {
        if (categoryRepository.existsById(category.getId())){
            List<Record> records = recordRepository.findRecordsByCategory(category);
            return records;
        }else throw new CategoryNotFoundException(category.getId());
    }

    public List<Record> getRecordsByUser(User user) {
        if (userRepository.existsById(user.getId())){
            List<Record> records = recordRepository.findRecordsByUser(user);
            return records;
        }else throw new UserNotFoundException(user.getId());
    }

    public List<Record> getRecordsByUserAndCategory(User user, Category category) {
        if (userRepository.existsById(user.getId())){
            if (categoryRepository.existsById(category.getId())){
                List<Record> records = recordRepository.findRecordsByUserAndCategory(user, category);
                if (!records.isEmpty()){
                    return records;
                }else throw new NotFoundRecordsException(user.getId(), category.getId());
            }else throw new CategoryNotFoundException(category.getId());
        }else throw new UserNotFoundException(user.getId());

    }
}
