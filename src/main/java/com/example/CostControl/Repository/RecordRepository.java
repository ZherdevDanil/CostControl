package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Exception.NotFoundRecordsException;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends CrudRepository<Record,Long> {
    Optional<Record> findById(Long id);

    void deleteById(Long id);

    Record save(Record record);

    List<Record> findRecordsByUserIdAndCategoryId(Long userId , Long categoryId );

    List<Record> findRecordsByUserId(Long userId);

    List<Record> findRecordsByCategoryId(Long categoryId);

    List<Record> findAll();

    @Override
    boolean existsById(Long id);

    /*
    private final FakeDataBase fakeDataBase;

    public RecordRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public Optional<Record> findById(long id){
        return fakeDataBase.getRecords().stream().filter(record -> record.getId()==id).findFirst();


    public void deleteById(long id){
        findById(id).ifPresent(fakeDataBase.getRecords()::remove);
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
    }*/
}
