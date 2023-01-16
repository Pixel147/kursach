package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@NoArgsConstructor
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user)
    {
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUserType());
        userRepository.save(user);
        return user;
    }
}
