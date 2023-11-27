package com.example.CostControl.Service;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Repository.RecordRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Record getRecordById(long id){
        return recordRepository.findById(id);
    }

    public void deleteRecordById(long id){
        recordRepository.deleteById(id);
    }

    public void addNewRecord(Record record){
        recordRepository.save(record);
    }



}
