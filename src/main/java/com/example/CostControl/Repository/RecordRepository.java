package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepository {
    private final FakeDataBase fakeDataBase;

    public RecordRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public Record findById(long id){
        Record record = new Record();
        for (Record record1: fakeDataBase.getRecords()) {
            if (record1.getId()==id){
                record=record1;
            }
        }
        return record;
    }


    public void deleteById(long id){
        fakeDataBase.getRecords().remove(findById(id));
    }


    public void save(Record record){
        fakeDataBase.getRecords().add(record);
    }
}
