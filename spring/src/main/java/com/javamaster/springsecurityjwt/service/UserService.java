package com.javamaster.springsecurityjwt.service;

import com.javamaster.springsecurityjwt.entity.User;
import com.javamaster.springsecurityjwt.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    public User findByLoginAndPassword(String username, String password) {
        User user = findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
