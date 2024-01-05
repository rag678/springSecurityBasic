package com.learn.springbootsecuritylear.services;

import com.learn.springbootsecuritylear.models.CustomUserDetail;
import com.learn.springbootsecuritylear.models.User;
import com.learn.springbootsecuritylear.repo.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustommUserdetailService implements UserDetailsService {
    @Autowired
    private Userrepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userrepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("user is New");
        }
        return new CustomUserDetail(user);
    }
}
