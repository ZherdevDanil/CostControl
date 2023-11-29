package com.example.CostControl.Service;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.IncorrectInputDataException;
import com.example.CostControl.Exception.UserNotFoundException;
import com.example.CostControl.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUserById(Long id) {
        if (!isUserExistsById(id)) {
            throw new UserNotFoundException(id);
        } else {
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
}
