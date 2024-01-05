package com.learn.springbootsecuritylear.controllers;

import com.learn.springbootsecuritylear.models.User;
import com.learn.springbootsecuritylear.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //all users
    @GetMapping("/")
    public List<User> getAllUsers(){
        return this.userService.getAllUser();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }
    @PostMapping("/")
    public User addNewuser(@RequestBody User user){
        return this.userService.addNewUser(user);
    }
}
