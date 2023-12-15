package com.example.CostControl.Repository;

import com.example.CostControl.Entity.Account;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account save(Account account);

    Optional<Account> findById(Long id);

    @Transactional
    void deleteAccountById(Long id);

}
