package com.example.CostControl.Service;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Exception.RecordNotFoundException;
import com.example.CostControl.Repository.RecordRepository;
import com.example.CostControl.Util.GenerateRandomValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    private final GenerateRandomValue generateRandomValue;

    public RecordService(RecordRepository recordRepository, GenerateRandomValue generateRandomValue) {
        this.recordRepository = recordRepository;
        this.generateRandomValue = generateRandomValue;
    }

    public Record getRecordById(long id) {
        return recordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteRecordById(long id) {
        recordRepository.deleteById(id);
    }

    public Record addNewRecord(Record record) {
        recordRepository.save(record);
        return record;
    }

    public List<Record> getRecordsByCategoryId(long categoryId) {
        return recordRepository.findRecordsByCategoryId(categoryId);
    }

    public List<Record> getRecordsByUserId(long userId) {
        return recordRepository.findRecordsByUserId(userId);
    }

    public List<Record> getRecordsByUserIdAndCategoryId(long userId, long categoryId) {
        return recordRepository.findRecordsByUserIdAndCategoryId(userId, categoryId);
    }

    public long generateNewUniqueRecordId() {
        return generateRandomValue.generateRandomUniqueNumber(recordRepository.getListOfRecordId());
    }


}
