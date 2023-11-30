package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Category;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import jakarta.transaction.Transactional;
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
    Record save(Record record) throws IllegalArgumentException;

    @Override
    List<Record> findAll();

    @Override
    boolean existsById(Long id);

    List<Record> findRecordsByUserAndCategory(User user, Category category);

    List<Record> findRecordsByUser(User user);

    List<Record> findRecordsByCategory(Category category);

    void delete(Record record);

    void deleteAllByUser(User user);

}
