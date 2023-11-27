package com.example.CostControl.Repository;

import com.example.CostControl.Entity.User;
import com.example.CostControl.Util.FakeDataBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final FakeDataBase fakeDataBase;

    public UserRepository(FakeDataBase fakeDataBase) {
        this.fakeDataBase = fakeDataBase;
    }

    public User findById(long id){
        User user1=new User();
        for (User user:fakeDataBase.getUsers()) {
            if (user.getId()==id){
                user1=user;
            }
        }
        return user1;
    }

    public List<User> findAll(){
        return fakeDataBase.getUsers();
    }

    public void delete(long id){
        fakeDataBase.getUsers().remove(findById(id));
    }

    public void save(User user){
        fakeDataBase.getUsers().add(user);
    }

    public List<Long> getListOfUsersId(){
        List<Long> list=new ArrayList<>();
        for (User user:fakeDataBase.getUsers()) {
            list.add(user.getId());
        }
        return list;
    }



}
