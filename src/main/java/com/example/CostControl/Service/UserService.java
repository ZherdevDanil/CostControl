package com.example.CostControl.Service;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Exception.UserNotFoundException;
import com.example.CostControl.Repository.UserRepository;
import com.example.CostControl.Util.GenerateRandomValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GenerateRandomValue generateRandomValue;

    public UserService(UserRepository userRepository, GenerateRandomValue generateRandomValue) {
        this.userRepository = userRepository;
        this.generateRandomValue = generateRandomValue;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveNewUser(User user) {
        userRepository.save(user);
    }

    public long generateNewUniqueUserId() {
        return generateRandomValue.generateRandomUniqueNumber(userRepository.getListOfUsersId());
    }
}
