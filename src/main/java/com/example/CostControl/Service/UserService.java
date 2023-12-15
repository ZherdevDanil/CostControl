package com.example.CostControl.Service;

import com.example.CostControl.Entity.Account;
import com.example.CostControl.Entity.Record;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.IncorrectInputDataException;
import com.example.CostControl.Exception.UserNotFoundException;
import com.example.CostControl.Repository.AccountRepository;
import com.example.CostControl.Repository.RecordRepository;
import com.example.CostControl.Repository.RoleRepository;
import com.example.CostControl.Repository.UserRepository;
import javax.transaction.Transactional;

import com.example.CostControl.dto.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserService implements UserDetailsService {
    private  UserRepository userRepository;

    private AccountRepository accountRepository;

    private  RecordRepository recordRepository;

    private  RoleRepository roleRepository;

    private  PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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

    public Optional<User> findByUsername(String username){
        return userRepository.findByName(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found with such name"));
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegistrationUserDto registrationUserDto){
        User user=  new User();
        user.setName(registrationUserDto.getName());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        Account account = new Account();
        account.setMoneyAmount(registrationUserDto.getAccount());
        Account savedAccount = accountRepository.save(account);
        user.setAccount(savedAccount);
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        return userRepository.save(user);
    }
}
