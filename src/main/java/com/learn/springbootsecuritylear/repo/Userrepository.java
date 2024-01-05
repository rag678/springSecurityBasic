package com.learn.springbootsecuritylear.repo;

import com.learn.springbootsecuritylear.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
