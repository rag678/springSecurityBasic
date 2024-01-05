package com.learn.springbootsecuritylear.services;

import com.learn.springbootsecuritylear.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<User> userList = new ArrayList<>();

    public UserService() {
        userList.add(new User("abc","abc","ABC@gmail.com"));
        userList.add(new User("xyz","abcxyz","XYZ@gmail.com"));
    }
    // get all users
    public List<User> getAllUser() {
        return this.userList;
    }
    //get single User
    public User getUser(String username){
        return this.userList.stream().filter((user -> user.getUsername().equals(username))).findAny().orElse(null);
    }
    // add new User
    public User addNewUser(User user){
        this.userList.add(user);
        return user;
    }
}
