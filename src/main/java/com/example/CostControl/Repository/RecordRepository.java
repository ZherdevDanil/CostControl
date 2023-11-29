package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {
    @Override
    Optional<Record> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Record save(Record record);

    @Override
    List<Record> findAll();

    @Override
    boolean existsById(Long id);

    List<Record> findRecordsByUserIdAndCategoryId(Long userId, Long categoryId);

    List<Record> findRecordsByUserId(Long userId);

    List<Record> findRecordsByCategoryId(Long categoryId);

}
