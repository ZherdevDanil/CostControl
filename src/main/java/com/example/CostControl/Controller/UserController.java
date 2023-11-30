package com.example.CostControl.Controller;

import com.example.CostControl.Entity.Account;
import com.example.CostControl.Entity.User;
import com.example.CostControl.Service.AccountService;
import com.example.CostControl.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final AccountService accountService;

    public UserController(UserService userService,
                          AccountService accountService) {
        this.userService = userService;
        this.accountService=accountService;
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
    public User addNewUser(@RequestParam("name") String name,
                           @RequestParam("money") Double moneyAmount) {
        User user = new User();
        user.setName(name);
        Account account = new Account();
        account.setMoneyAmount(moneyAmount);
        Account savedAccount = accountService.saveNewAccount(account);
        user.setAccount(savedAccount);
        userService.saveNewUser(user);
        return user;
    }

    @PostMapping("/user/{id}/add-money")
    public User addMoney(@PathVariable("id") Long id ,
                         @RequestParam("money") Double money){
        User user = userService.getUserById(id);
        user.getAccount().setMoneyAmount(user.getAccount().getMoneyAmount()+money);
        userService.updateUser(user);
        return user;
    }
}
