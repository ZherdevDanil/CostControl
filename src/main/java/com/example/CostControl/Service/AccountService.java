package com.example.CostControl.Service;

import com.example.CostControl.Entity.Account;
import com.example.CostControl.Repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveNewAccount(Account account) {
        return accountRepository.save(account);
    }


}
