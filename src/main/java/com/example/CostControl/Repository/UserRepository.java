package com.example.CostControl.Repository;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final FakeDataBase fakeDataBase;

    public UserRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public Optional<User> findById(long id) {
        return fakeDataBase.getUsers().stream().filter(user -> user.getId() == id).findFirst();
    }

    public List<User> findAll() {
        return fakeDataBase.getUsers();
    }

    public void delete(long id) {
        findById(id).ifPresent(fakeDataBase.getUsers()::remove);
    }

    public void save(User user) {
        fakeDataBase.getUsers().add(user);
    }

    public List<Long> getListOfUsersId() {
        List<Long> list = new ArrayList<>();
        for (User user : fakeDataBase.getUsers()) {
            list.add(user.getId());
        }
        return list;
    }


}
