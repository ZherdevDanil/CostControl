package com.example.CostControl.Controller;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @PostMapping("/user")
    public User addNewUser(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        userService.saveNewUser(user);
        return user;
    }
}
