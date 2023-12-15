package com.example.CostControl.Service;

import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.IncorrectInputDataException;
import com.example.CostControl.Exception.UserNotFoundException;
import com.example.CostControl.Repository.AccountRepository;
import com.example.CostControl.Repository.RecordRepository;
import com.example.CostControl.Repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private AccountRepository accountRepository;

    private final RecordRepository recordRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, RecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.recordRepository = recordRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (!isUserExistsById(id)) {
            throw new UserNotFoundException(id);
        } else {
            User user = userRepository.findById(id).get();
            accountRepository.deleteAccountById(user.getAccount().getId());
            user.setAccount(null);
            updateUser(user);
            List<Record> records = recordRepository.findRecordsByUser(user);
            for (Record record : records) {
                record.setCategory(null);
                recordRepository.save(record);
            }
            recordRepository.deleteAllByUser(user);
            userRepository.deleteById(id);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveNewUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IncorrectInputDataException(user.toString());
        }

    }

    public boolean isUserExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
