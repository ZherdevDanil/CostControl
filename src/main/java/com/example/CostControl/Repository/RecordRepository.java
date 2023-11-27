package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Exception.NotFoundRecordsException;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RecordRepository {
    private final FakeDataBase fakeDataBase;

    public RecordRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public Optional<Record> findById(long id){
        return fakeDataBase.getRecords().stream().filter(record -> record.getId()==id).findFirst();
        /*
        Record record = new Record();
        for (Record record1: fakeDataBase.getRecords()) {
            if (record1.getId()==id){
                record=record1;
            }
        }
        return record;*/
    }


    public void deleteById(long id){
        findById(id).ifPresent(fakeDataBase.getRecords()::remove);
        /*fakeDataBase.getRecords().remove(findById(id));*/
    }


    public Record save(Record record){
        fakeDataBase.getRecords().add(record);
        return record;

    }

    public List<Record> findRecordsByUserId(long userId) {
        List<Record> records = new ArrayList<>();
        for (Record record : fakeDataBase.getRecords()) {
            if (record.getUserId() == userId) {
                records.add(record);
            }
        }
        if (records.isEmpty()){
            throw new NotFoundRecordsException("userId",userId);
        }
        return records;
    }

    public List<Record> findRecordsByCategoryId(long categoryId){
        List<Record> records = new ArrayList<>();
        for (Record record : fakeDataBase.getRecords()) {
            if (record.getCategoryId() == categoryId) {
                records.add(record);
            }
        }
        if (records.isEmpty()){
            throw new NotFoundRecordsException("categoryId",categoryId);
        }
        return records;
    }

    public List<Record> findRecordsByUserIdAndCategoryId(long userId,long categoryId){
        List<Record> records = new ArrayList<>();
        for (Record record:fakeDataBase.getRecords()) {
            if (record.getUserId() == userId){
                if (record.getCategoryId() == categoryId){
                    records.add(record);
                }
            }
        }
        if (records.isEmpty()){
           throw new NotFoundRecordsException(userId,categoryId);
        }
        return records;
    }


    public List<Long> getListOfRecordId(){
        List<Long> list=new ArrayList<>();
        for (Record record:fakeDataBase.getRecords()) {
            list.add(record.getId());
        }
        return list;
    }
}
