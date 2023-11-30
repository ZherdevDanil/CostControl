package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Account;
import com.example.CostControl.Entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.DialectOverride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    @Override
    List<User> findAll();


    @Transactional
    @Override
    void deleteById(Long id);

    @Override
    User save(User user);

    @Override
    boolean existsById(Long id);

}
