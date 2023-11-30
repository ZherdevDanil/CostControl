package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Account;
import com.example.CostControl.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account , Long> {

    Account save(Account account);

    Optional<Account> findById(Long id);

    @Transactional
    void deleteAccountById(Long id);

}
