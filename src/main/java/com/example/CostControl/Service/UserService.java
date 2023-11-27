package com.example.CostControl.Service;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Repository.UserRepository;
import com.example.CostControl.Util.GenerateRandomValue;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GenerateRandomValue generateRandomValue;

    public UserService(UserRepository userRepository,GenerateRandomValue generateRandomValue) {
        this.userRepository = userRepository;
        this.generateRandomValue=generateRandomValue;
    }

    public User getUserById(long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(long id){
        userRepository.delete(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void saveNewUser(User user){
        userRepository.save(user);
    }
    public long generateNewUniqueUserId(){
        return generateRandomValue.generateRandomUniqueNumber(userRepository.getListOfUsersId());
    }
/*
    public long generateNewUniqueUserId(){
        long newUniqueUserId = new Random().nextLong() % 1001;
        if (newUniqueUserId < 0) {
            newUniqueUserId = -newUniqueUserId;
        }
        if (userRepository.getListOfUsersId().contains(newUniqueUserId)){
            generateNewUniqueUserId();
        }
        return newUniqueUserId;
    }*/

}
