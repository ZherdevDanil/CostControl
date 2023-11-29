package com.example.CostControl.Service;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Exception.*;
import com.example.CostControl.Repository.CategoryRepository;
import com.example.CostControl.Repository.RecordRepository;
import com.example.CostControl.Repository.UserRepository;
import com.example.CostControl.Util.GenerateRandomValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;


    public RecordService(RecordRepository recordRepository,CategoryRepository categoryRepository ,UserRepository userRepository) {
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
        }else{
            recordRepository.deleteById(id);
        }

    }

    public Record addNewRecord(Record record) {
        try {
            recordRepository.save(record);

        }catch (Exception e){
            throw new IncorrectInputDataException(record.toString());
        }
        return record;
    }

    public List<Record> getRecordsByCategoryId(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)){
            throw new CategoryNotFoundException(categoryId);
        }else {
            List<Record> records = recordRepository.findRecordsByCategoryId(categoryId);
            if (records.isEmpty()) {
                throw new NotFoundRecordsException("categoryId", categoryId);
            } else {
                return records;
            }
        }
    }

    public List<Record> getRecordsByUserId(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }else {
            List<Record> records = recordRepository.findRecordsByUserId(userId);
            if (records.isEmpty()) {
                throw new NotFoundRecordsException("userId", userId);
            } else {
                return records;
            }
        }
    }

    public List<Record> getRecordsByUserIdAndCategoryId(Long userId, Long categoryId) {
        if (!userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        } else if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }else {
            List<Record> records = recordRepository.findRecordsByUserIdAndCategoryId(userId, categoryId);
            if (records.isEmpty()) {
                throw new NotFoundRecordsException(userId, categoryId);
            } else {
                return records;
            }
        }
    }
}
